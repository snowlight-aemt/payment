package com.snowlightpay.banking.adapter.out.external.bank;

import lombok.Data;

@Data
public class BankAccount {

    private final String backName;
    private final String bankAccountNumber;
    private final boolean isValid;

    public BankAccount(String backName, String bankAccountNumber, boolean isValid) {
        this.backName = backName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
    }
}
