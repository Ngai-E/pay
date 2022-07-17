package com.ngai.payment.services;

import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.services.custom.CallbackEvent;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class CallbackService implements ApplicationListener<CallbackEvent> {
    private static final Logger LOG = LogManager.getLogger(CallbackService.class);
    @Override
    public void onApplicationEvent(CallbackEvent event) {
        LOG.info("received event to push callback");

        String url = event.getCallbackUrl();
        PaymentResponse response = event.getPaymentResponse();

        postCallbackFeedback(url, response);
    }

    private void postCallbackFeedback(String url, PaymentResponse response) {
        try {
            HttpClient httpClient = HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .responseTimeout(Duration.ofMillis(5000))
                    .doOnConnected(conn ->
                            conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                    .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
            WebClient client =
                    WebClient.builder()
                    .baseUrl(url)
                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
            WebClient.RequestBodySpec bodySpec = uriSpec.uri("");

            WebClient.RequestHeadersSpec headersSpec = bodySpec.body(
                    BodyInserters.fromPublisher(Mono.just(response),
                    PaymentResponse.class));

            Mono<String> res = headersSpec.retrieve()
                    .bodyToMono(String.class);
        } catch (Exception exception) {
            LOG.error("error while post callback {}", exception.getMessage());
        }
    }

}
