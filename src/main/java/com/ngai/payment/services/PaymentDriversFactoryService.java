package com.ngai.payment.services;

import com.ngai.payment.model.entity.TPaymentProviders;
import com.ngai.payment.model.repository.TPaymentProvidersRepository;
import com.ngai.payment.services.payment.core.contract.ICallbackPayment;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentDriversFactoryService {
    private final Map<String, MobilePaymentImpl> mapOfMobilePayments;
    private final Map<String, ICallbackPayment> mapOfICallbacks;
    private final TPaymentProvidersRepository paymentProvidersRepository;

    @Autowired
    public PaymentDriversFactoryService(List<MobilePaymentImpl> listOfAvailablePayments,
                                        List<ICallbackPayment> iCallbackPaymentList,
                                        TPaymentProvidersRepository paymentProvidersRepository) {
        this.mapOfMobilePayments
                = listOfAvailablePayments.stream()
                .collect(Collectors.toMap(MobilePaymentImpl::getDriverCode, Function.identity()));
        this.mapOfICallbacks
                = iCallbackPaymentList.stream()
                .collect(Collectors.toMap(ICallbackPayment::getDriverCode, Function.identity()));

        this.paymentProvidersRepository = paymentProvidersRepository;
    }
    
    public MobilePaymentImpl getMobilePaymentProcessByPaymenCode(String paymentCode) {
        MobilePaymentImpl mobilePayment = this.mapOfMobilePayments.getOrDefault(paymentCode, null);

        return mobilePayment;
    }

    public ICallbackPayment getICallbackByPaymenCode(String paymentCode) {
        return this.mapOfICallbacks.getOrDefault(paymentCode, null);
    }
    
}
