package com.ngai.payment.services.payment.drivers.monetbil.payment.dto;

import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetBillEnums.CallbackEnumStatus;
import lombok.Data;

@Data
public class MonentBillCallbackResponse {
    private String service;
    private String transaction_id;
    private String transaction_uuid;
    private String phone;
    private String amount;
    private String fee;
    private String revenue;
    private CallbackEnumStatus status;
    private String message;
    private String country_name;
    private String country_iso;
    private String country_code;
    private String mccmnc ;
    private String operator;
    private String operator_transaction_id;
    private String currency;
    private String item_ref;
    private String payment_ref;
    private String first_name;
    private String email;
    private String sign;
}
