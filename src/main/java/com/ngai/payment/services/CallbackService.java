package com.ngai.payment.services;

import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.services.custom.CallbackEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CallbackService implements ApplicationListener<CallbackEvent> {
    private static final Logger LOG = LogManager.getLogger(CallbackService.class);
    private final RestTemplate restTemplate;

    public CallbackService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    @Override
    public void onApplicationEvent(CallbackEvent event) {
        LOG.info("received event to push callback");

        String url = event.getCallbackUrl();
        PaymentResponse response = event.getPaymentResponse();

        postCallbackFeedback(url, response);
    }

    public void postCallbackFeedback(String url, PaymentResponse response) {
        try  {
            HttpEntity<PaymentResponse> request = new HttpEntity<>(response);
            this.restTemplate.postForEntity(url, request, String.class);
        } catch (RestClientException exception) {
            LOG.error("error while post callback {}", exception.getMessage());
        }
    }

}
