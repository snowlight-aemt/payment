package com.snowlightpay.money.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {
    private String targetMembershipId;
    private int moneyChangingAmount;

    public IncreaseMoneyRequestCommand(@NotNull String targetMembershipId, @NotNull int moneyChangingAmount) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingAmount = moneyChangingAmount;
        this.validateSelf();
    }
}
