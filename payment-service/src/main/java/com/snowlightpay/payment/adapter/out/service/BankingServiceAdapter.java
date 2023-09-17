package com.snowlightpay.payment.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.payment.application.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.payment.application.port.out.RegisteredBankAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BankingServiceAdapter implements GetRegisteredBankAccountPort {
    private final CommonHttpClient commonHttpClient;
    private final String bankingServiceUrl;

    public BankingServiceAdapter(CommonHttpClient commonHttpClient,
                                 @Value("${service.banking.url}") String bankingServiceUrl) {
        this.commonHttpClient = commonHttpClient;
        this.bankingServiceUrl = bankingServiceUrl;
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(String membership) {
        log.info("getRegisteredBankAccount ");
        String url = String.join("/", bankingServiceUrl, "banking/account", membership);
        try {
            String jsonResponse = this.commonHttpClient.sendGetRequest(url).body();
            log.info("getMembership {}", jsonResponse);

            System.out.println(jsonResponse);
            ObjectMapper mapper = new ObjectMapper();
            RegisteredBankAccountResponse registeredBankAccountResponse = mapper.readValue(jsonResponse, RegisteredBankAccountResponse.class);

            return new RegisteredBankAccount(
                    registeredBankAccountResponse.getRegisteredBankAccountId(),
                    registeredBankAccountResponse.getMembershipId(),
                    registeredBankAccountResponse.getBankName(),
                    registeredBankAccountResponse.getBankAccountNumber()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
