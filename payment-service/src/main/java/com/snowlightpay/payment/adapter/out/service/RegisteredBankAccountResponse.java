package com.snowlightpay.payment.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredBankAccountResponse {
    private String registeredBankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
    private String aggregateIdentifier;
}



