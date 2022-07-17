package com.ngai.payment.services.payment.core;

import com.ngai.payment.model.entity.TTrace;
import lombok.Data;

@Data
public class PaymentContext {
    private String tel;
    private String traceId;
    private TTrace tTrace;
    private String refTransactionId;
    private String error;
    private String paymentCode;
    private double amount;
    private String pin;
    private String callbackUrl;
    private String providerMsg;
    private String paymentStatus;
    private String extTransaction;

    public boolean hasError() {
        return this.error != null && !this.error.isEmpty();
    }
}
