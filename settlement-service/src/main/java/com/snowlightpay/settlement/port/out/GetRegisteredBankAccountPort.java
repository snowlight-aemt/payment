package com.snowlightpay.settlement.port.out;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membership);

    void requestFirmBanking(String bankName, String bankNumber, int moneyAmount);
}
