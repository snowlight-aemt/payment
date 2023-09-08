package com.snowlightpay.money.adapter.axon.event;

import com.snowlightpay.common.SelfValidating;
import com.snowlightpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedEvent extends SelfValidating<MemberMoneyCreatedCommand> {
    @NotNull
    private String memberShipId;

    public MemberMoneyCreatedEvent(@NotNull String memberShipId) {
        this.memberShipId = memberShipId;
        super.validateSelf();
    }

    public MemberMoneyCreatedEvent() {
    }
}
