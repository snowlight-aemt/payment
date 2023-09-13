package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.banking.domain.RegisterBankAccount;

public interface GetRegisteredBankAccountUseCase {
     RegisterBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
