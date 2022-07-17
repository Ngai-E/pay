package com.ngai.payment.services.payment.drivers.monetbil.payment.feign;

import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetBillCheckPaymentRequest;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetBillCheckPaymentResponse;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetbillPaymentRequestDto;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetbillPaymentResponseDto;
import feign.Param;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "MonetBillFeignClient", configuration = MonetBillFeignConfig.class , url="https://api.monetbil.com/payment/v1")
public interface MonetBillFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "/placePayment")
    MonetbillPaymentResponseDto placePayment(MonetbillPaymentRequestDto paymentRequestDto);

    @RequestMapping(method = RequestMethod.POST, value = "/checkPayment",  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    MonetBillCheckPaymentResponse checkPayment(MultiValueMap<String, String> checkPaymentRequest);

}
