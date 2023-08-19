package com.snowlightpay.money.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {
    private String targetMembershipId;
    private int moneyChangingAmount;
}
