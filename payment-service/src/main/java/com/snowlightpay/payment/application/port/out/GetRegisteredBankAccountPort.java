package com.snowlightpay.payment.application.port.out;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccount getRegisteredBankAccount(String membership);
}
