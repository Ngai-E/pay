package com.ngai.payment.services.payment.core.impl;

import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.model.entity.TPaymentProviders;
import com.ngai.payment.model.entity.TTrace;
import com.ngai.payment.model.entity.TTraceStatus;
import com.ngai.payment.model.repository.TTraceRepository;
import com.ngai.payment.model.repository.TTraceStatusRepository;
import com.ngai.payment.services.custom.ApiException;
import com.ngai.payment.services.payment.core.PaymentContext;
import com.ngai.payment.services.payment.core.contract.IPayment;
import com.ngai.payment.services.payment.core.enums.PAYMENT_STATUS;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;

public abstract class MobilePaymentImpl implements IPayment {
    protected   TTraceStatusRepository tTraceStatusRepository;
    protected  TTraceRepository tTraceRepository;
    protected String driverCode;

    protected PaymentContext context;
    protected TPaymentProviders tPaymentProviders;

    public MobilePaymentImpl(TTraceStatusRepository tTraceStatusRepository,
                             TTraceRepository tTraceRepository) {
        this.tTraceStatusRepository = tTraceStatusRepository;
        this.tTraceRepository = tTraceRepository;
    }

    protected void updatePaymentStatus(String traceId, PAYMENT_STATUS status){
        TTraceStatus tTraceStatus = new TTraceStatus(UUID.randomUUID().toString());
        tTraceStatus.setDtDateCreated(new Date());
        tTraceStatus.setLgTraceId(traceId);
        tTraceStatus.setStrStatus(status.name());
        tTraceStatus.setStrExternalTransaction(this.context != null ? context.getExtTransaction() : "");
        tTraceStatus.setStrMsg(this.context != null ? context.getProviderMsg() : "");
        tTraceStatus.setStrExtCode(this.context != null ? context.getPaymentStatus() : "");
        gettTraceStatusRepository().save(tTraceStatus);
    }


    //<editor-fold desc="[ OVERRIDE FINAL METHODS]" defaultstate="collapsed">
    @Override
    public final void makePayment(MobilePaymentRequest mobilePaymentRequest) {
        this.context = buildPaymentContext(mobilePaymentRequest);

        //<editor-fold defaultstate="collapsed" desc=" [VALIDATION] ">
        if (gettPaymentProviders() == null) {
            this.context.setError("PAYMENT PROVIDER NOT PROVIDED");
            return;
        }

        if (!gettPaymentProviders().getBActive()) {
            this.context.setError("PAYMENT PROVIDER NOT ACTIVE");
            return;
        }

        if (!gettPaymentProviders().getBCashin()) {
            this.context.setError("PAYMENT PROVIDER NOT CONFIGURED FOR CASHIN TRANSACTIONS");
            return;
        }

        if (context.getAmount() <= 0) {
            this.context.setError(("AMOUNT MUST BE GREATER THAN 0"));
            return;
        }

        if (context.getAmount() < gettPaymentProviders().getDbMinDepositAmount() || context.getAmount() > gettPaymentProviders().getDbMaxDepositAmount()) {
            this.context.setError("AMOUNT MUST MEET PROVIDER LIMITS");
        }
        //</editor-fold>

        TTrace tTrace = new TTrace(context.getTraceId());
        tTrace.setBCallbackReceived(false);
        tTrace.setStrCallbackUrl(context.getCallbackUrl());
        tTrace.setDtDateCreated(new Date());
        tTrace.setStrPhoneNumber(this.context.getTel());
        tTrace.setLgPaymentProviders(this.gettPaymentProviders());
        tTrace.setStrOriginatingTransaction(this.context.getRefTransactionId());
        tTrace.setDbAmount(this.context.getAmount());


        gettTraceRepository().save(tTrace);
        updatePaymentStatus(tTrace.getLgTraceId(), PAYMENT_STATUS.PROCESSING);

        proceedExternal(context);

        if (context.hasError()){
            updatePaymentStatus(tTrace.getLgTraceId(), PAYMENT_STATUS.PROCESSING);
            return;
        }

        updatePaymentStatus(tTrace.getLgTraceId(), PAYMENT_STATUS.SENT);

    }

    @Override
    public final void checkPaymentStatus(String origTransacationRef) {
        Optional<TTrace> optTrace = tTraceRepository.findByStrOriginatingTransaction(origTransacationRef);
        
        if (optTrace.isEmpty()) {
            throw new ApiException("NOT A VALID TRANSACTION", HttpStatus.NOT_FOUND);
        }
        
        //todo create a method to check status externally
    }

    public final PaymentContext getContext() {
        return context;
    }
    
    //</editor-fold>

    //<editor-fold desc="[ ABSTRACT METHODS]" defaultstate="collapsed">
      public abstract PaymentContext buildPaymentContext(MobilePaymentRequest request);
      public abstract PaymentResponse buildPaymentResponse(PaymentContext paymentContext);
      public abstract void proceedExternal(PaymentContext paymentContext);
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="[ GETTERS AND SETTERS ]">
    public TPaymentProviders gettPaymentProviders() {
        return tPaymentProviders;
    }

    public TTraceStatusRepository gettTraceStatusRepository() {
        return tTraceStatusRepository;
    }

    public void settTraceStatusRepository(TTraceStatusRepository tTraceStatusRepository) {
        this.tTraceStatusRepository = tTraceStatusRepository;
    }

    public TTraceRepository gettTraceRepository() {
        return tTraceRepository;
    }

    public void settTraceRepository(TTraceRepository tTraceRepository) {
        this.tTraceRepository = tTraceRepository;
    }


    public String getDriverCode() {
        return this.driverCode;
    }
    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }
    //</editor-fold>


}
