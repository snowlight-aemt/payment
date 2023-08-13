package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {
    private final RegisteredBankAccountRepository backAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createBankAccount(RegisterBankAccount.BankAccountId bankAccountId,
                                                            RegisterBankAccount.MembershipId membershipId,
                                                            RegisterBankAccount.BankName bankName,
                                                            RegisterBankAccount.BankAccountNumber bankAccountNumber,
                                                            RegisterBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return backAccountRepository.save(new RegisteredBankAccountJpaEntity(Long.parseLong(membershipId.getMembershipId()),
                bankName.getBackName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid()));
    }
}
