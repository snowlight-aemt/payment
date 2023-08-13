package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership  modifyMembership(ModifyMembershipCommand command);
}
