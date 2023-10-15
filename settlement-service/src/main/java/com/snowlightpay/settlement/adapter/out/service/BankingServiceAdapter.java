package com.snowlightpay.settlement.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.settlement.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.settlement.port.out.RegisteredBankAccountAggregateIdentifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    public RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membership) {
        String url = String.join("/", bankingServiceUrl, "banking/account", membership);
        try {
            String jsonResponse = this.commonHttpClient.sendGetRequest(url).body();

            System.out.println(jsonResponse);
            ObjectMapper mapper = new ObjectMapper();
            RegisteredBankAccount registeredBankAccount = mapper.readValue(jsonResponse, RegisteredBankAccount.class);

            return new RegisteredBankAccountAggregateIdentifier(
                    registeredBankAccount.getRegisteredBankAccountId(),
                    registeredBankAccount.getAggregateIdentifier(),
                    registeredBankAccount.getMembershipId(),
                    registeredBankAccount.getBankName(),
                    registeredBankAccount.getBankAccountNumber()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
