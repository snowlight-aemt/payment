package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembershipByMembershipId(FindMembershipCommand command);
}
