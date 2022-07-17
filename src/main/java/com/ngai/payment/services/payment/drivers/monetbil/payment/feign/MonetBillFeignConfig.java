package com.ngai.payment.services.payment.drivers.monetbil.payment.feign;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;


public class MonetBillFeignConfig {

    public MonetBillFeignConfig() {
    }
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
//            requestTemplate.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        };
    }

    
}
