package com.ngai.payment.services.payment.drivers.monetbil.payment.dto;

public enum MonetbillPaymentStatus {
    REQUEST_ACCEPTED,
    INVALID_REQUEST,
    INVALID_SERVICE,
    MISSING_SERVICE,
    INVALID_MSISDN,
    MISSING_MSISDN,
    INVALID_COUNTRY,
    INVALID_AMOUNT,
    SERVICE_DISABLED_BY_ADMIN,
    SERVICE_DISABLED_BY_OWNER,
    SERVICE_NOT_FOUND,
    SERVICE_NOT_APPROVED,
    SERVER_ERROR

}
