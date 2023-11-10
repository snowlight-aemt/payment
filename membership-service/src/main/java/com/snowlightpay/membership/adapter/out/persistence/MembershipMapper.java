package com.snowlightpay.membership.adapter.out.persistence;

import com.snowlightpay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipJpaEntity membershipJpaEntity) {
        return Membership.generateMember(
                new Membership.MembershipId(membershipJpaEntity.getMembershipId().toString()),
                new Membership.MembershipName(membershipJpaEntity.getName()),
                new Membership.MembershipEmail(membershipJpaEntity.getEmail()),
                new Membership.MembershipAddress(membershipJpaEntity.getAddress()),
                new Membership.MembershipValid(membershipJpaEntity.isValid()),
                new Membership.MembershipCorp(membershipJpaEntity.isCorp()),
                new Membership.MembershipRefreshToken(membershipJpaEntity.getRefreshToken())
        );
    }
}
