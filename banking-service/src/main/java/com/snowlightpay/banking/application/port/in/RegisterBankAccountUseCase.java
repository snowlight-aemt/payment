package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.banking.domain.RegisterBankAccount;

public interface RegisterBankAccountUseCase {
    RegisterBankAccount createBankAccount(RegisterBankAccountCommand common);
}
