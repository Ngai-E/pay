package com.ngai.payment.services;

import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentDriversFactoryServiceTest {

    @Autowired
    PaymentDriversFactoryService paymentDriversFactoryService;

    @Test
    public void testGetDriverClass() {
        MobilePaymentImpl paymentProcessor = paymentDriversFactoryService.getMobilePaymenProcess("MonetBillPayment");

        System.out.println(paymentProcessor.getDriverName());
    }

}