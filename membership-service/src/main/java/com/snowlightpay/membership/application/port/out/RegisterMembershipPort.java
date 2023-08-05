package com.snowlightpay.membership.application.port.out;

import com.snowlightpay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.snowlightpay.membership.domain.Membership;

public interface RegisterMembershipPort {
    MembershipJpaEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipValid membershipValid,
            Membership.MembershipCorp membershipCorp
    );

    MembershipJpaEntity findMemberByMembershipId(Membership.MembershipId membershipId);
}
