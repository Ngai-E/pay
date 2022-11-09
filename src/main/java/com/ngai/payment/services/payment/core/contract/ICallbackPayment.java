package com.ngai.payment.services.payment.core.contract;

public interface ICallbackPayment<T> {
    public void processCallback(T callbackResponse);
    public String getDriverCode();
}
