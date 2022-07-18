/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ngai.payment.services;

import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.services.custom.CallbackEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author SOFT
 */
@SpringBootTest
public class CallbackServiceTest {
    @Autowired
    CallbackService instance;
    /**
     * Test of onApplicationEvent method, of class CallbackService.
     */
    @Test
    public void testOnApplicationEvent() {
        System.out.println("onApplicationEvent");
        CallbackEvent event = null;
        instance.onApplicationEvent(event);
    }

    /**
     * Test of postCallbackFeedback method, of class CallbackService.
     */
    @Test
    public void testPostCallbackFeedback() {
        System.out.println("postCallbackFeedback");
        String url = "";
        PaymentResponse response = new PaymentResponse();
        
        response.setPaymentRef("1222");
        response.setStatus("SUCCESS");
        instance.postCallbackFeedback("http://localhost:50640/v1/callback/test", response);
    }
    
}
