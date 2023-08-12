package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
    @NotEmpty
    private final String name;
    @NotEmpty
    private final String email;
    @NotEmpty
    private final String address;

    @AssertTrue
    private final boolean isValid;
    private final boolean isCorp;

    public RegisterMembershipCommand(String name, String email, String address, boolean isValid, boolean isCorp) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;

        this.validateSelf();
    }
}
