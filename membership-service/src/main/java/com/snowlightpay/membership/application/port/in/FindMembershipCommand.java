package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
    private String membershipId;

    public FindMembershipCommand(String membershipId) {
        this.membershipId = membershipId;

        this.validateSelf();
    }
}
