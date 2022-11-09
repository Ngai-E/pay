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


}
