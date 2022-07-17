package com.ngai.payment.services.payment.drivers.monetbil.payment.dto;

import lombok.Data;

@Data
public class MonetbillPaymentResponseDto {

       private MonetbillPaymentStatus status;
        private String message;
        private String channel_ussd;
        private String channel_name;
        private String channel;
        private String paymentId;

}
