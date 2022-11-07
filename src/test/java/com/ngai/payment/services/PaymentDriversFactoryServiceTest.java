package com.ngai.payment.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.services.custom.ApiException;
import com.ngai.payment.services.payment.core.contract.ICallbackPayment;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import com.ngai.payment.services.payment.drivers.monetbil.payment.MonetBillPayment;
import com.ngai.payment.utils.ErrorCodes;
import com.ngai.payment.utils.Parameters;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;

@SpringBootTest
class PaymentDriversFactoryServiceTest {
    
    @Autowired
    PaymentDriversFactoryService instance;

    @Autowired
    PaymentDriversFactoryService paymentDriversFactoryService;
    
    @Autowired
    MonetBillPayment monetbill;

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetDriverClass() {
        MobilePaymentImpl paymentProcessor = paymentDriversFactoryService.getMobilePaymenProcess("MonetBillPayment");

        System.out.println(paymentProcessor.getDriverName());
    }

    /**
     * Test of getMobilePaymenProcess method, of class PaymentDriversFactoryService.
     */
    @Test
    public void testGetMobilePaymenProcess() {
        System.out.println("getMobilePaymenProcess");
        String driverClassName = "";
        MobilePaymentImpl expResult = null;
        MobilePaymentImpl result = instance.getMobilePaymenProcess(driverClassName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMobilePaymentProcessByPaymenCode method, of class PaymentDriversFactoryService.
     */
    @Test
    public void testGetMobilePaymentProcessByPaymenCode() throws JsonProcessingException {
        System.out.println("getMobilePaymentProcessByPaymenCode");
        String paymentCode = "100";
        MobilePaymentImpl expResult = null;
//        MobilePaymentImpl paymentDriver = instance.getMobilePaymentProcessByPaymenCode(paymentCode);
        MobilePaymentImpl paymentDriver = monetbill;
        
                System.out.println(paymentDriver.gettPaymentProviders().getStrPaymentType());
        
        if (paymentDriver == null)
            throw new ApiException(Parameters.PAYMENT_N0T_SUPPORTED, HttpStatus.BAD_REQUEST);

        paymentDriver.makePayment(getRequest());

        if (paymentDriver.getContext().hasError()) {
            throw new ApiException(paymentDriver.getContext().getError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println(paymentDriver.buildPaymentResponse());
        
        assertNotNull(paymentDriver.gettPaymentProviders());
    }

    /**
     * Test of getICallbackByPaymenCode method, of class PaymentDriversFactoryService.
     */
    @Test
    public void testGetICallbackByPaymenCode() {
        System.out.println("getICallbackByPaymenCode");
        String paymentCode = "";
        ICallbackPayment expResult = null;
        ICallbackPayment result = instance.getICallbackByPaymenCode(paymentCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

}