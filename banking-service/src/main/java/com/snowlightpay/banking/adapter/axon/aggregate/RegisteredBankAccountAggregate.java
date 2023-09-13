package com.snowlightpay.banking.adapter.axon.aggregate;

import com.snowlightpay.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.snowlightpay.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.snowlightpay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
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

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        System.out.println("CreateRegisteredBankAccountEvent Sourcing Handle");
        this.id = UUID.randomUUID().toString();
        this.membershipId = event.getMembershipId();
        this.bankName = event.getBankName();
        this.bankAccountNumber = event.getBankAccountNumber();
    }
}
