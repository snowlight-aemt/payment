package com.snowlightpay.membership.adapter.out.persistence;

import com.snowlightpay.common.PersistenceAdapter;
import com.snowlightpay.membership.application.port.out.FindMembershipByAddressPort;
import com.snowlightpay.membership.application.port.out.RegisterMembershipPort;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipByAddressPort {
    private final MembershipRepository membershipRepository;
    private final MembershipMapper membershipMapper;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                 Membership.MembershipEmail membershipEmail,
                                 Membership.MembershipAddress membershipAddress,
                                 Membership.MembershipValid membershipValid,
                                 Membership.MembershipCorp membershipCorp) {
        return membershipRepository.save(new MembershipJpaEntity(membershipName.getMembershipName(),
                                                                membershipEmail.getMembershipEmail(),
                                                                membershipAddress.getMembershipAddress(),
                                                                membershipValid.isMembershipValid(),
                                                                membershipCorp.isMembershipCorp(),
                                                                membershipAddress.getMembershipAddress()));
    }

    @Override
    public MembershipJpaEntity findMemberByMembershipId(Membership.MembershipId membershipId) {
        return membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()))
                                        .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId,
                                                Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipValid membershipValid,
                                                Membership.MembershipCorp membershipCorp,
                                                Membership.MembershipRefreshToken membershipRefreshToken) {
        MembershipJpaEntity entity =
                membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()))
                                            .orElseThrow(IllegalArgumentException::new);

        entity.setName(membershipName.getMembershipName());
        entity.setEmail(membershipEmail.getMembershipEmail());
        entity.setAddress(membershipAddress.getMembershipAddress());
        entity.setValid(membershipValid.isMembershipValid());
        entity.setCorp(membershipCorp.isMembershipCorp());
        entity.setRefreshToken(membershipRefreshToken.getMembershipRefreshToken());

        return membershipRepository.save(entity);
    }

    @Override
    public List<Membership> findMemberByAddress(Membership.MembershipAddress membershipAddress) {
        return membershipRepository.findByAddress(membershipAddress.getMembershipAddress())
                .stream().map(membershipMapper::mapToDomainEntity).collect(Collectors.toList());

    }
}
