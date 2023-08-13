package com.snowlightpay.backing.application.port.out;

import com.snowlightpay.backing.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.snowlightpay.backing.domain.RegisterBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity createBankAccount(
            RegisterBankAccount.BankAccountId bankAccountId,
            RegisterBankAccount.MembershipId membershipId,
            RegisterBankAccount.BankName bankName,
            RegisterBankAccount.BankAccountNumber bankAccountNumber,
            RegisterBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
