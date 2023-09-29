package com.snowlightpay.membership.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.snowlightpay.membership.adapter.out.persistence.MembershipMapper;
import com.snowlightpay.membership.application.port.in.FindMembershipByAddressCommand;
import com.snowlightpay.membership.application.port.in.FindMembershipCommand;
import com.snowlightpay.membership.application.port.in.FindMembershipUseCase;
import com.snowlightpay.membership.application.port.out.FindMembershipByAddressPort;
import com.snowlightpay.membership.application.port.out.RegisterMembershipPort;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final FindMembershipByAddressPort findMembershipByAddressPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembershipByMembershipId(FindMembershipCommand command) {
        MembershipJpaEntity membership = registerMembershipPort.findMemberByMembershipId(
                new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(membership);
    }

    @Override
    public List<Membership> findMembershipByAddress(FindMembershipByAddressCommand command) {
        Membership.MembershipAddress membershipAddress = new Membership.MembershipAddress(command.getAddress());
        return findMembershipByAddressPort.findMemberByAddress(membershipAddress);
    }
}
