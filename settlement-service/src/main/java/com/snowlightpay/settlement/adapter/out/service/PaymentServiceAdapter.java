package com.snowlightpay.settlement.adapter.out.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.settlement.port.out.PaymentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentServiceAdapter implements PaymentPort {
    private final CommonHttpClient commonHttpClient;
    @Value("${service.payment.url}")
    private String paymentUrl;

    @Override
    public List<Payment> payment() {
        try {
            String url = String.join("/", paymentUrl, "payment/normal-status");
            String body = this.commonHttpClient.sendGetRequest(url).body();

            ObjectMapper objectMapper = new ObjectMapper();
            List<Payment> payments = objectMapper.readValue(body, new TypeReference<List<Payment>>() {});
            log.info("payment");
            log.info(payments.toString());

            return payments;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment updatePaymentStatus(PaymentIdRequest request) {
//        PaymentIdRequest paymentIdRequest = new PaymentIdRequest(request);
        String url = String.join("/", paymentUrl, "payment/finish-settlement");
        try {
            String req = new ObjectMapper().writeValueAsString(request);
            String body = this.commonHttpClient.sendPostRequest(url, req).get().body();

            log.info("updatePaymentSTatus");
            log.info(body);
            return null;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
