package com.ngai.payment.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse {
    private String errorCode;
    private String message;
    private Object data;
}
