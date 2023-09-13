package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.snowlightpay.banking.application.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.banking.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, GetRegisteredBankAccountPort {
    private final RegisteredBankAccountRepository backAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createBankAccount(RegisterBankAccount.BankAccountId bankAccountId,
                                                            RegisterBankAccount.MembershipId membershipId,
                                                            RegisterBankAccount.BankName bankName,
                                                            RegisterBankAccount.BankAccountNumber bankAccountNumber,
                                                            RegisterBankAccount.LinkedStatusIsValid linkedStatusIsValid,
                                                            RegisterBankAccount.AggregateIdentifier aggregateIdentifier) {
        return backAccountRepository.save(new RegisteredBankAccountJpaEntity(Long.parseLong(membershipId.getMembershipId()),
                bankName.getBackName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid(),
                aggregateIdentifier.getAggregateIdentifier()));
    }

    @Override
    public List<RegisteredBankAccountJpaEntity> getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        return this.backAccountRepository.findByMembershipId(Long.parseLong(command.getMembershipId()));
    }
}
