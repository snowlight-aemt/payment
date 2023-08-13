package com.snowlightpay.backing.application.port.in;

import com.snowlightpay.backing.domain.RegisterBankAccount;

public interface RegisterBankAccountUseCase {
    RegisterBankAccount createBankAccount(RegisterBankAccountCommand common);
}
