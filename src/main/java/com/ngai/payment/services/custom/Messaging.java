package com.ngai.payment.services.custom;

import lombok.Data;

@Data
public abstract class Messaging {
    private String message;
    private String errCode;
    private String DetailMessage;


}
