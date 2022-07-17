package com.ngai.payment.services.payment.drivers.monetbil.payment.dto;

import lombok.Data;

@Data
public class MonetbillPaymentRequestDto {
    private String service;
    private String phonenumber;
    private String amount;
    private String notify_url;
    private String payment_ref;
}
