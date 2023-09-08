package com.snowlightpay.money.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {
    @NotNull
    private final String memberShipId;

    public CreateMemberMoneyCommand(@NotNull String memberShipId) {
        this.memberShipId = memberShipId;
        super.validateSelf();
    }
}
