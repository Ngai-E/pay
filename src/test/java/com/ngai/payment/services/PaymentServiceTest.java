/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.ngai.payment.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.model.dto.PaymentResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ngaielizabeth
 */
@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    PaymentService instance;
    
    public PaymentServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of handlePayment method, of class PaymentService.
     */
    @Test
    public void testHandlePayment() throws JsonProcessingException {
        System.out.println("handlePayment");
        MobilePaymentRequest request = getRequest();
        PaymentResponse result = instance.handlePayment(request);
        assertNotNull(result);
    }

        private MobilePaymentRequest getRequest() throws JsonProcessingException {
            MobilePaymentRequest request = new ObjectMapper().readValue("{\n" +
                    "  \"amount\": 1,\n" +
                    "  \"callbackUrl\": \"http://localhost:50640/v1/callback/test\",\n" +
                    "  \"customerId\": \"123456789\",\n" +
                    "  \"origRef\": \"12354\",\n" +
                    "  \"paymentCode\": \"100\",\n" +
                    "  \"tel\": \"237650931636\"\n" +
                    "}", MobilePaymentRequest.class);
            return request;
        }

    /**
     * Test of handleStatusCheck method, of class PaymentService.
     */
    @Test
    public void testHandleStatusCheck() {
        System.out.println("handleStatusCheck");
        String origTransacationRef = "";
        PaymentService instance = new PaymentService();
        instance.handleStatusCheck(origTransacationRef);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
