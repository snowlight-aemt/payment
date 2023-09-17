package com.snowlightpay.banking.application.port.out;

import com.snowlightpay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.snowlightpay.banking.application.port.in.GetRegisteredBankAccountCommand;

import java.util.List;

public interface GetRegisteredBankAccountPort {
    List<RegisteredBankAccountJpaEntity> getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
