package com.ngai.payment.services.payment.core.contract;

import com.ngai.payment.model.dto.MobilePaymentRequest;

public interface IPayment {
    /**
     * This method debits userExternalAccount
     */
    public void makePayment(MobilePaymentRequest request);

    /**
     * This method checks if a previous call for debiting his account was successful
     */
    public void checkPaymentStatus(String origTransacationRef);
}
