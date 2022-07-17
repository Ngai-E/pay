package com.ngai.payment;

import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
@EnableWebSecurity
public class PaymentApplication {

	public static void main(String[] args) {

		SpringApplication.run(PaymentApplication.class, args);
	}

}
