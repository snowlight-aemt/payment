package com.snowlightpay.banking.adapter.axon.aggregate;

import com.snowlightpay.banking.adapter.axon.command.FirmBankingRequestCreatedCommand;
import com.snowlightpay.banking.adapter.axon.command.FirmBankingUpdatedCommand;
import com.snowlightpay.banking.adapter.axon.event.FirmBankingRequestCreatedEvent;
import com.snowlightpay.banking.adapter.axon.event.FirmBankingUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
public class FirmBackingRequestAggregate {
    @AggregateIdentifier
    private String id;

    private String firmBankingId;
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;
    private int firmBankingStatus; // 0 요청 1 완료 2 실패

    @CommandHandler
    public FirmBackingRequestAggregate(FirmBankingRequestCreatedCommand command) {
        apply(new FirmBankingRequestCreatedEvent(command.getFromBankAccountNumber(),
                                                    command.getFromBankName(),
                                                    command.getToBankAccountNumber(),
                                                    command.getToBankName(),
                                                    command.getMoneyAmount()));
    }

    @CommandHandler
    public String update(FirmBankingUpdatedCommand command) {
        this.id = command.getAggregateIdentifier();

        apply(new FirmBankingUpdatedEvent(command.getFirmBankingId(),
                command.getAggregateIdentifier(),
                command.getStatus()));
        return this.id;
    }

    @EventSourcingHandler
    public void on(FirmBankingRequestCreatedEvent event) {
        this.id = UUID.randomUUID().toString();

        this.fromBankAccountNumber = event.getFromBankAccountNumber();
        this.fromBankName = event.getFromBankName();
        this.toBankAccountNumber = event.getToBankAccountNumber();
        this.toBankName = event.getToBankName();
        this.moneyAmount = event.getMoneyAmount();
    }

    @EventSourcingHandler
    public void on(FirmBankingUpdatedEvent event) {
        this.firmBankingStatus = event.getStatus();
    }

    public FirmBackingRequestAggregate() {
    }
}
