package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.domain.RegisterBankAccount;
import org.springframework.stereotype.Component;

@Component
public class RegisteredBackAccountMapper {
    public RegisterBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity) {
        return RegisterBankAccount.generateMember(
                new RegisterBankAccount.BankAccountId(registeredBankAccountJpaEntity.getBackAccountId()+""),
                new RegisterBankAccount.MembershipId(registeredBankAccountJpaEntity.getMembershipId().toString()),
                new RegisterBankAccount.BankName(registeredBankAccountJpaEntity.getBankName()),
                new RegisterBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
                new RegisterBankAccount.LinkedStatusIsValid(registeredBankAccountJpaEntity.isLinkedStatusIsValid()),
                new RegisterBankAccount.AggregateIdentifier(registeredBankAccountJpaEntity.getAggregateIdentifier())
        );
    }
}
