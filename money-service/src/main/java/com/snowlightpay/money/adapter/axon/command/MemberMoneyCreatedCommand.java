package com.snowlightpay.money.adapter.axon.command;

import com.snowlightpay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand extends SelfValidating<MemberMoneyCreatedCommand> {
    @NotNull
    private String memberShipId;

    public MemberMoneyCreatedCommand(@NotNull String memberShipId) {
        this.memberShipId = memberShipId;
        super.validateSelf();
    }

    public MemberMoneyCreatedCommand() {
    }
}
