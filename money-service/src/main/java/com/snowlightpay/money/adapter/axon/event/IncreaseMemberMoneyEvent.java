package com.snowlightpay.money.adapter.axon.event;

import com.snowlightpay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMemberMoneyEvent {
    private  String aggregateIdentifier;
    private  String targetMembershipId;
    private  int amount;

    public IncreaseMemberMoneyEvent(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
    }

    public IncreaseMemberMoneyEvent() {
    }
}
