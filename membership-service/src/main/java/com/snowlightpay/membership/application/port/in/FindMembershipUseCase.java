package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipUseCase {
    Membership findMembershipByMembershipId(FindMembershipCommand command);
    List<Membership> findMembershipByAddress(FindMembershipByAddressCommand command);
}
