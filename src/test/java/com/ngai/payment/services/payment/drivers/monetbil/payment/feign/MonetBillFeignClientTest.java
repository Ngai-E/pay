/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ngai.payment.services.payment.drivers.monetbil.payment.feign;

import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetBillCheckPaymentRequest;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetBillCheckPaymentResponse;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetbillPaymentRequestDto;
import com.ngai.payment.services.payment.drivers.monetbil.payment.dto.MonetbillPaymentResponseDto;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *
 * @author SOFT
 */
@SpringBootTest
public class MonetBillFeignClientTest {
    @Autowired
    MonetBillFeignClient instance;
    
    /**
     * Test of placePayment method, of class MonetBillFeignClient.
     */
    @Test
    public void testPlacePayment() {
        System.out.println("placePayment");
        MonetbillPaymentRequestDto paymentRequestDto = null;
        MonetbillPaymentResponseDto expResult = null;
        MonetbillPaymentResponseDto result = instance.placePayment(paymentRequestDto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPayment method, of class MonetBillFeignClient.
     */
    @Test
    public void testCheckPayment() {
        System.out.println("checkPayment");
        MonetBillCheckPaymentRequest checkPaymentRequest = new MonetBillCheckPaymentRequest();
        checkPaymentRequest.setPaymentId("22071607312543742336");
        
        MultiValueMap<String, String> mp = new LinkedMultiValueMap<>();
        mp.add("paymentId", "22071607312543742336");
        MonetbillPaymentResponseDto expResult = null;
        MonetBillCheckPaymentResponse result = instance.checkPayment(mp);
        System.out.println(result);
        assertNotNull(result);
        assertNotNull(result.getTransaction());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    
}
