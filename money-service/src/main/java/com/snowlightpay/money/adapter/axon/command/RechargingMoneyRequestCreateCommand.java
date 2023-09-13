package com.snowlightpay.money.adapter.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.transaction.annotation.Transactional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingMoneyRequestCreateCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private String rechargingRequestId;
    private String membershipId;
    private int amount;
}
