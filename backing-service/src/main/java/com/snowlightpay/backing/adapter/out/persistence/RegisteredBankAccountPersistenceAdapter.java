package com.snowlightpay.backing.adapter.out.persistence;

import com.snowlightpay.backing.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.backing.domain.RegisterBankAccount;
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
