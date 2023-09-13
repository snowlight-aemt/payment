package com.snowlightpay.banking.application.service;

import com.snowlightpay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.snowlightpay.banking.adapter.out.external.bank.BankAccount;
import com.snowlightpay.banking.adapter.out.persistence.RegisteredBackAccountMapper;
import com.snowlightpay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.snowlightpay.banking.application.port.in.RegisterBankAccountCommand;
import com.snowlightpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.snowlightpay.banking.application.port.out.GetMembershipPort;
import com.snowlightpay.banking.application.port.out.MembershipStatus;
import com.snowlightpay.banking.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.banking.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class RegisterBackingAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBackAccountMapper registeredBackAccountMapper;

    private final RegisterBankAccountInfoPort registerBankAccountInfoPort;

    private final GetMembershipPort getMembershipPort;

    private final CommandGateway commandGateway;

    @Override
    public RegisterBankAccount createBankAccount(RegisterBankAccountCommand command) {

        // ToDo 맴버쉽 체크 membershipId
        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

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

    @Override
    public void createBankAccountByEvent(RegisterBankAccountCommand command) {
        var axonCommand = new CreateRegisteredBankAccountCommand(command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber());

        this.commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                registerBankAccountPort.createBankAccount(
                        new RegisterBankAccount.BankAccountId(command.getBankAccountId()),
                        new RegisterBankAccount.MembershipId(command.getMembershipId()),
                        new RegisterBankAccount.BankName(command.getBankName()),
                        new RegisterBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                        new RegisterBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
                );
            }
        });
    }
}
