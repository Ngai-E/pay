package com.ngai.payment.controllers;

import com.ngai.payment.model.dto.ApiResponse;
import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.services.PaymentService;
import com.ngai.payment.utils.Utility;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework .http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/mobile")
public class MobilePaymentController {
    private static final Logger LOG = LogManager.getLogger(MobilePaymentController.class);

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<ApiResponse> makeMobilePayments(@RequestBody MobilePaymentRequest body) {
        LOG.info("incoming payment request with body {}", body);

        ApiResponse apiResponse = Utility.buildApiResponse(paymentService, paymentService.handlePayment(body));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
