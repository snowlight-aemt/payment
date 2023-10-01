package com.snowlightpay.money.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RechargingMoneyRequestCreateCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String rechargingRequestId;
    private String membershipId;
    private int amount;
}
