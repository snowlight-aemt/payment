package com.snowlightpay.common.event;

import lombok.AllArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.lang.annotation.Target;

@AllArgsConstructor
public class CheckRegisteredBankAccountCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier; // RegisteredBankAccount

    private String rechargeRequestId;
    private String membershipId;
}
