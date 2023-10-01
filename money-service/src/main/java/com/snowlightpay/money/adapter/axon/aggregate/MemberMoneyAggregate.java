package com.snowlightpay.money.adapter.axon.aggregate;

import com.snowlightpay.money.adapter.axon.command.IncreaseMemberMoneyCommand;
import com.snowlightpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.snowlightpay.money.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import com.snowlightpay.money.adapter.axon.event.IncreaseMemberMoneyEvent;
import com.snowlightpay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import com.snowlightpay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.snowlightpay.money.application.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@Data
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;
    private Long membershipId;
    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(MemberMoneyCreatedCommand command) {
        System.out.println("MemberMoneyCreatedCommand Handler");
        // Event Store
        apply(new MemberMoneyCreatedEvent(command.getMemberShipId()));
    }

    @CommandHandler
    public String handle(@NotNull IncreaseMemberMoneyCommand command) {
        System.out.println("IncreaseMemberMoneyCommand Handler");
        id = command.getAggregateIdentifier();
        System.out.println("IncreaseMemberMoneyCommand ID : " + id);

        apply(new IncreaseMemberMoneyEvent(id, command.getMembershipId(), command.getAmount()));
        return id;
    }

    // Start Saga
    @CommandHandler
    public void handle(RechargingMoneyRequestCreateCommand command,
                         GetRegisteredBankAccountPort getRegisteredBankAccountPort) {
        log.info("RechargingMoneyRequestCreateCommand START");
        this.id = command.getAggregateIdentifier();

        log.info("RechargingMoneyRequestCreateCommand ID : " + id);
        log.info("getMembershipId ID : " + command.getMembershipId());

        RegisteredBankAccountAggregateIdentifier registeredBankAccount =
                                getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());

        log.info("RechargingMoneyRequestCreateCommand MIDDLE");
        apply(new RechargingRequestCreatedEvent(command.getRechargingRequestId(),
                                                    command.getMembershipId(),
                                                    command.getAmount(),
                                                    registeredBankAccount.getAggregateIdentifier(),
                                                    registeredBankAccount.getBankName(),
                                                    registeredBankAccount.getBankAccountNumber()));
        log.info("RechargingMoneyRequestCreateCommand END");
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMemberShipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMemberMoneyEvent event) {
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    public MemberMoneyAggregate() {
    }
}
