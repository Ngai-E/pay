package com.ngai.payment.services.custom;

import com.ngai.payment.model.dto.PaymentResponse;
import org.springframework.context.ApplicationEvent;

public class CallbackEvent extends ApplicationEvent {
    private PaymentResponse paymentResponse;
    private String callbackUrl;
    public CallbackEvent(Object source, PaymentResponse response, String callbackUrl) {
        super(source);
        this.paymentResponse = response;
        this.callbackUrl = callbackUrl;
    }

    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }
}
