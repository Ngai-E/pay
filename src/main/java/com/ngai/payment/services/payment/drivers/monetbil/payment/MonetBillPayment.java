package com.ngai.payment.services.payment.drivers.monetbil.payment;

import com.ngai.payment.components.ParamsCache;
import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.model.entity.TReceivedCallback;
import com.ngai.payment.model.entity.TTrace;
import com.ngai.payment.model.repository.TReceivedCallbackRepository;
import com.ngai.payment.model.repository.TTraceRepository;
import com.ngai.payment.model.repository.TTraceStatusRepository;
import com.ngai.payment.services.custom.CallbackEvent;
import com.ngai.payment.services.payment.core.PaymentContext;
import com.ngai.payment.services.payment.core.contract.ICallbackPayment;
import com.ngai.payment.services.payment.core.enums.PAYMENT_STATUS;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.*;
import com.ngai.payment.services.payment.drivers.monetbil.payment.feign.MonetBillFeignClient;
import com.ngai.payment.utils.Parameters;
import com.ngai.payment.utils.Utility;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@Component
@Transactional
public class MonetBillPayment extends MobilePaymentImpl implements ICallbackPayment<MonentBillCallbackResponse> {
    private static final Logger LOG = LogManager.getLogger();

    MonetBillFeignClient monetBillFeignClient;
    TReceivedCallbackRepository tReceivedCallbackRepository;
    ApplicationEventPublisher applicationEventPublisher;
    @PersistenceContext
    EntityManager em;

    public MonetBillPayment(TTraceRepository tTraceRepository,
                            TTraceStatusRepository tTraceStatusRepository,
                            MonetBillFeignClient monetBillFeignClient,
                            ApplicationEventPublisher applicationEventPublisher,
                            TReceivedCallbackRepository tReceivedCallbackRepository) {
        super(tTraceStatusRepository, tTraceRepository, MonetBillPayment.class.getSimpleName());

        this.monetBillFeignClient = monetBillFeignClient;
        this.applicationEventPublisher = applicationEventPublisher;
        this.tReceivedCallbackRepository = tReceivedCallbackRepository;
    }


    //<editor-fold defaultstate="collapsed" desc="[ OVERRIDE METHODS]">
    @Override
    public PaymentContext buildPaymentContext(MobilePaymentRequest request) {
        PaymentContext paymentContext = new PaymentContext();

        paymentContext.setAmount(request.getAmount());
        paymentContext.setPaymentCode(request.getPaymentCode());
        paymentContext.setRefTransactionId(request.getOrigRef());
        paymentContext.setCallbackUrl(request.getCallbackUrl());
        paymentContext.setTel(request.getTel());
        paymentContext.setTraceId(UUID.randomUUID().toString());

        return paymentContext;
    }

    @Override
    public void proceedExternal() {
        PaymentContext paymentContext = super.getContext();

        if (getContext().hasError()) return;

        MonetbillPaymentRequestDto paymentRequestDto = new MonetbillPaymentRequestDto();

        paymentRequestDto.setAmount(String.valueOf(getContext().getAmount()));
        paymentRequestDto.setNotify_url(String.format((String) ParamsCache.getParam(Parameters.KEY_CALLBACK_BASE_URL),
                gettPaymentProviders().getStrPaymentCode(),
                paymentContext.getTraceId())
        );
        paymentRequestDto.setPhonenumber(getContext().getTel());
        paymentRequestDto.setService((String) ParamsCache.getParam(Parameters.KEY_SECRET_URL));
        paymentRequestDto.setPayment_ref(getContext().getTraceId());

        MonetbillPaymentResponseDto paymentResponseDto = monetBillFeignClient.placePayment(paymentRequestDto);

        if (paymentResponseDto == null){
            paymentContext.setError("PAYMENT FAILED");
            return;
        }

        if (!MonetbillPaymentStatus.REQUEST_ACCEPTED.equals( paymentResponseDto.getStatus())){
            paymentContext.setError(!Utility.isNullOrEmpty(paymentResponseDto.getMessage()) ? paymentResponseDto.getMessage() : "PAYMENT FAILED");
        }

        paymentContext.setPaymentStatus(paymentResponseDto.getStatus().name());
        paymentContext.setProviderMsg(paymentResponseDto.getMessage());
        paymentContext.setExtTransaction(paymentResponseDto.getPaymentId());


    }

    @Override
    public PaymentResponse buildPaymentResponse() {
        PaymentResponse paymentResponse = new PaymentResponse();

        paymentResponse.setPaymentRef(super.getContext().getTraceId());
        paymentResponse.setStatus(super.getContext().getProviderMsg());

        return paymentResponse;
    }

    @Override
    public void processCallback(MonentBillCallbackResponse callbackResponse) {
        boolean isReceived = validateCallback(callbackResponse.getPayment_ref(), callbackResponse.getStatus().name());
        
        if (!isReceived) return;
        
        Optional<TTrace> optTrace = super.tTraceRepository.findById(callbackResponse.getPayment_ref());

        if (optTrace.isEmpty()){
            LOG.info("No transaction found with trace id {}", callbackResponse.getPayment_ref());
            return;
        }

        if(optTrace.get().getBCallbackReceived()){
            LOG.info("callback processed");
            return;
        }

        optTrace.get().setBCallbackReceived(true);
        tTraceRepository.save(optTrace.get());

        PaymentResponse paymentResponse = new PaymentResponse();

        PAYMENT_STATUS payment_status = MonetBillEnums.CallbackEnumStatus.success.equals(callbackResponse.getStatus()) ? PAYMENT_STATUS.VALIDATED : PAYMENT_STATUS.FAILED;

        updatePaymentStatus(callbackResponse.getPayment_ref(), payment_status);

        paymentResponse.setPaymentRef(optTrace.get().getStrOriginatingTransaction());
        paymentResponse.setStatus(payment_status.name());

        applicationEventPublisher.publishEvent(new CallbackEvent(this, paymentResponse, optTrace.get().getStrCallbackUrl()));
    }
    //</editor-fold>
    public boolean validateCallback(String paymentRef, String status) {
        try {
            TReceivedCallback tReceivedCallback = new TReceivedCallback();
            tReceivedCallback.setLgtraceId(paymentRef);
            tReceivedCallback.setStrStatus(status);
            tReceivedCallback.setDtDateCreated(new Date());

            em.persist(tReceivedCallback);
            return true;
        } catch (DataIntegrityViolationException | EntityExistsException ex) {
            if (ex.getMessage().contains(SQLIntegrityConstraintViolationException.class.getSimpleName())){
                LOG.error("callback already processed for {}", paymentRef);
            }
            else LOG.error("error while processing inserting callback {}, {}", paymentRef, status);

        }

        return false;
    }
}
