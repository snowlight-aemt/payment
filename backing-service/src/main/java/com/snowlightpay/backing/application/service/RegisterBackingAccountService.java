package com.snowlightpay.backing.application.service;

import com.snowlightpay.backing.adapter.out.extenal.bank.BankAccount;
import com.snowlightpay.backing.adapter.out.persistence.RegisteredBackAccountMapper;
import com.snowlightpay.backing.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.snowlightpay.backing.application.port.in.RegisterBankAccountCommand;
import com.snowlightpay.backing.application.port.in.RegisterBankAccountUseCase;
import com.snowlightpay.backing.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.backing.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.backing.domain.RegisterBankAccount;
import com.snowlightpay.common.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterBackingAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBackAccountMapper registeredBackAccountMapper;

    private final RegisterBankAccountInfoPort registerBankAccountInfoPort;

    @Override
    public RegisterBankAccount createBankAccount(RegisterBankAccountCommand command) {

        // ToDo 맴버쉽 체크 membershipId

        BankAccount bankAccount = registerBankAccountInfoPort.findRegisterBankAccountInfo(
                new RegisterBankAccount.BankName(command.getBankName()),
                new RegisterBankAccount.BankAccountNumber(command.getBankAccountNumber()));

        if (bankAccount.isValid()) {
            RegisteredBankAccountJpaEntity membership = registerBankAccountPort.createBankAccount(
                    new RegisterBankAccount.BankAccountId(command.getBankAccountId()),
                    new RegisterBankAccount.MembershipId(command.getMembershipId()),
                    new RegisterBankAccount.BankName(command.getBankName()),
                    new RegisterBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisterBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );

            return registeredBackAccountMapper.mapToDomainEntity(membership);
        } else {
            return null;
        }
    }
}
