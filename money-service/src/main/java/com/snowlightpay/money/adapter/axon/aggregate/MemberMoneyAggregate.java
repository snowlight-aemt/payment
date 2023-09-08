package com.snowlightpay.money.adapter.axon.aggregate;

import com.snowlightpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.snowlightpay.money.adapter.axon.event.MemberMoneyCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

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
        log.info("MemberMoneyCreatedCommand Handler");

        // Event Store
        apply(new MemberMoneyCreatedEvent(command.getMemberShipId()));
//        new MemberMoneyCreatedCommand(command.getMemberShipId());
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreatedEvent event) {
        log.info("MemberMoneyCreatedEvent Sourcing Handler");
//        id = event.
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMemberShipId());
        balance = 0;
    }

    public MemberMoneyAggregate() {
    }
}
