package com.snowlightpay.membership.application.port.out;

import com.snowlightpay.membership.domain.Membership;

import java.util.List;

public interface FindMembershipByAddressPort {
    List<Membership> findMemberByAddress(Membership.MembershipAddress membershipAddress);
}
