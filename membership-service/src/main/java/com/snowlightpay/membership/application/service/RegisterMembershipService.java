package com.snowlightpay.membership.application.service;

import com.snowlightpay.membership.UseCase;
import com.snowlightpay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.snowlightpay.membership.adapter.out.persistence.MembershipMapper;
import com.snowlightpay.membership.application.port.in.*;
import com.snowlightpay.membership.application.port.out.RegisterMembershipPort;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase, FindMembershipUseCase, ModifyMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership createMembership(RegisterMembershipCommand  command) {
        MembershipJpaEntity membership = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipValid(command.isValid()),
                new Membership.MembershipCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(membership);
    }

    @Override
    public Membership findMembershipByMembershipId(FindMembershipCommand command) {
        MembershipJpaEntity membership = registerMembershipPort.findMemberByMembershipId(
                new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(membership);
    }

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = registerMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipValid(command.isValid()),
                new Membership.MembershipCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
