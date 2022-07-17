package com.ngai.payment.controllers;

import com.google.gson.Gson;
import com.ngai.payment.services.PaymentDriversFactoryService;
import com.ngai.payment.services.payment.core.contract.ICallbackPayment;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonentBillCallbackResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/callback")
public class CallbackController<T> {
    private static final Logger LOG = LogManager.getLogger(CallbackController.class);

    @Autowired
    PaymentDriversFactoryService paymentDriversFactoryService;

    @PostMapping(value = "/100/{traceId}", consumes = {/*MediaType.APPLICATION_FORM_URLENCODED_VALUE,*/ MediaType.APPLICATION_JSON_VALUE})
    public void treatCallbackMonetBill(@PathVariable(value = "traceId") String traceId,
                                       @RequestBody  MonentBillCallbackResponse monentBillCallbackResponse) {
        LOG.info("received callback {} for {}", new Gson().toJson(monentBillCallbackResponse), traceId);

        ICallbackPayment<MonentBillCallbackResponse> paymentDriver =  paymentDriversFactoryService.getICallbackByPaymenCode("100");

        if (paymentDriver == null){
            LOG.error("No payment driver to handle callback with payment code {} ", 100);
            return;
        }

        paymentDriver.processCallback(monentBillCallbackResponse);

    }
    
    @PostMapping("/test")
    public void testCallbackReception(@RequestBody String data){
        LOG.info(data);
    }
}
