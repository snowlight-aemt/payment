package com.snowlightpay.money.adapter.axon.command;

import com.snowlightpay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyCommand extends SelfValidating<MemberMoneyCreatedCommand> {

    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    @NotNull
    private String membershipId;
    @NotNull
    private int amount;

    public IncreaseMemberMoneyCommand(String aggregateIdentifier, String membershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.membershipId = membershipId;
        this.amount = amount;

        this.validateSelf();
    }
}