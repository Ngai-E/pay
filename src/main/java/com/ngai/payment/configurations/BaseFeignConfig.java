package com.ngai.payment.configurations;

import com.ngai.payment.components.ParamsCache;
import com.ngai.payment.services.payment.drivers.monetbil.payment.feign.MonetBillFeignClient;
import com.ngai.payment.utils.Parameters;
import feign.Feign;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseFeignConfig {
//    public BaseFeignConfig() {
//        Feign.builder()
//                      .encoder(new FormEncoder())
//                      .target(MonetBillFeignClient.class, (String) ParamsCache.getParam(Parameters.KEY_MONETBILL_BASE_URL));
//    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

}
