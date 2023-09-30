package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class FindMembershipByAddressCommand extends SelfValidating<FindMembershipByAddressCommand> {
    @NotNull
    private final String address;

    public FindMembershipByAddressCommand(String address) {
        this.address = address;

        this.validateSelf();
    }
}
