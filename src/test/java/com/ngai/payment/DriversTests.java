package com.ngai.payment;

import com.ngai.payment.services.PaymentDriversFactoryService;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import com.ngai.payment.services.payment.drivers.mtn.MtnPayment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriversTests {

    @Autowired
    PaymentDriversFactoryService driversFactoryService;

    @Test
    public void testGetDriverClass() {
        MobilePaymentImpl paymentProcessor = driversFactoryService.getMobilePaymenProcess("MonetBillPayment");

        System.out.println(paymentProcessor.getDriverName());
        paymentProcessor = driversFactoryService.getMobilePaymenProcess(MtnPayment.class.getSimpleName());

        System.out.println(paymentProcessor.getDriverName());
    }
}
