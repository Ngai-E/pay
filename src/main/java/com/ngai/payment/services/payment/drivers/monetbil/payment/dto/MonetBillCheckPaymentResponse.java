package com.ngai.payment.services.payment.drivers.monetbil.payment.dto;

import lombok.Data;

@Data
public class MonetBillCheckPaymentResponse {
    private String paymentId;
    private String message;
    private Transaction transaction;
}

@Data
class Transaction {
    private String transaction_UUID;
    private String created_date;
    private String start_time;
    private String end_time;
    private String msisdn;
    private String mobile_operator_name_short;
    private String mobile_operator_namemobile_network_code;
    private String mobile_operator_code;
    private String mobile_country_code;
    private String country_name;
    private String country_code;
    private String country_iso;
    private String user;
    private String amount;
    private String fee;
    private String revenue;
    private String currency;
    private String status;
    private String message;
    private String http_user_agent;
    private String device;
    private String device_constructor;
    private String device_model;
    private String os;
    private String os_version;
    private String browser;
    private String browser_version;
    private String ip_address;
    private String isp;
    private String isp_org;
    private String region_code;
    private String region_name;
    private String localisation_string;
    private String query_string;
    private String notify;
    private String item_ref;
    private String payment_ref;
    private String first_name;
    private String last_name;
    private String email;
    private String receipt_sent;
    private String api_call;

} 