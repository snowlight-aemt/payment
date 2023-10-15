package com.snowlightpay.settlement.port.out;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membership);
}
