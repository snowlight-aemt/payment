package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipUseCase> {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String email;

    private boolean valid;
    private boolean corp;

    private String refreshToken;

    public ModifyMembershipCommand(String id, String name, String address, String email, boolean valid, boolean corp, String refreshToken) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.valid = valid;
        this.corp = corp;
        this.refreshToken = refreshToken;

        this.validateSelf();
    }
}
