package com.snowlightpay.banking.adapter.axon.aggregate;

import com.snowlightpay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.snowlightpay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.snowlightpay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
import com.snowlightpay.banking.adapter.out.external.bank.BankAccount;
import com.snowlightpay.banking.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.event.CheckRegisteredBankAccountCommand;
import com.snowlightpay.common.event.CheckRegisteredBankAccountEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;

    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
        System.out.println("CreateRegisteredBankAccountCommand Command Handle");
        apply(new CreateRegisteredBankAccountEvent(command.getMembershipId(),
                                                    command.getBankName(),
                                                    command.getBankAccountNumber()));

    }

    @CommandHandler
    public void handle(@NotNull CheckRegisteredBankAccountCommand command, RegisterBankAccountInfoPort registerBankAccountInfoPort) {
        System.out.println("CheckRegisteredBankAccountCommand Handler");

        // command 를 통해, 어그리거트 가 정상인지를 확인한다.
        id = command.getAggregateIdentifier();

        // Check Registered Bank Account
        BankAccount bankAccount = registerBankAccountInfoPort.findRegisterBankAccountInfo(
                                        new RegisterBankAccount.BankName(command.getBankName()),
                                        new RegisterBankAccount.BankAccountNumber(command.getBankAccountNumber()));

        String firmbankingAggregateIdentifier = UUID.randomUUID().toString();

        // CheckRegisteredBankAccountEvent
        apply(new CheckRegisteredBankAccountEvent(
                command.getRechargeRequestId(),
                command.getCheckRegisteredBankAccountId(),
                command.getMembershipId(),
                bankAccount.isValid(),
                command.getAmount(),
                firmbankingAggregateIdentifier,
                bankAccount.getBackName(),
                bankAccount.getBankAccountNumber()
        ));
    }

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        System.out.println("CreateRegisteredBankAccountEvent Sourcing Handle");
        this.id = UUID.randomUUID().toString();
        this.membershipId = event.getMembershipId();
        this.bankName = event.getBankName();
        this.bankAccountNumber = event.getBankAccountNumber();
    }
}
