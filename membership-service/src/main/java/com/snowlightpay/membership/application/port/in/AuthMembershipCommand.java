package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import com.snowlightpay.membership.adapter.in.web.AuthMembershipRequest;
import lombok.Getter;

@Getter
public class AuthMembershipCommand extends SelfValidating<AuthMembershipCommand> {
    private String membershipId;

    public AuthMembershipCommand(String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
