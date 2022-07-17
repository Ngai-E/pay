package com.ngai.payment.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ngai.payment.model.dto.ApiExceptionResponseDto;
import com.ngai.payment.model.dto.ApiResponse;
import com.ngai.payment.services.custom.Messaging;

public class Utility {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public static ApiResponse buildApiResponse(Messaging messaging, Object data) {
        return ApiResponse.builder()
                .message(messaging.getMessage())
                .errorCode(messaging.getErrCode())
                .data(data)
                .build();
    }

    public static ApiExceptionResponseDto buildExceptionResponse(RuntimeException e) {
        return ApiExceptionResponseDto.builder()
                .message(e.getMessage())
                .build();
    }

    public static ApiExceptionResponseDto buildAllExceptionResponse(Exception e) {
        return ApiExceptionResponseDto.builder()
                .message(Parameters.SERVER_ERROR)
                .build();
    }

    public static boolean isNullOrEmpty(String data) {
        return data == null || data.isEmpty();
    }
}
