package com.ngai.payment.configurations;

import com.ngai.payment.services.custom.ApiException;
import com.ngai.payment.utils.Parameters;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
            case 404:
                return new ApiException(Parameters.INVALID_REQUEST, HttpStatus.NOT_FOUND);
            case 500:
                return new ApiException(Parameters.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            default:
                return new Exception("Generic error");
        }
    }
}
