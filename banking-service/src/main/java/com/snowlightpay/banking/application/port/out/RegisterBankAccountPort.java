package com.snowlightpay.banking.application.port.out;

import com.snowlightpay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.snowlightpay.banking.domain.RegisterBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity createBankAccount(
            RegisterBankAccount.BankAccountId bankAccountId,
            RegisterBankAccount.MembershipId membershipId,
            RegisterBankAccount.BankName bankName,
            RegisterBankAccount.BankAccountNumber bankAccountNumber,
            RegisterBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
