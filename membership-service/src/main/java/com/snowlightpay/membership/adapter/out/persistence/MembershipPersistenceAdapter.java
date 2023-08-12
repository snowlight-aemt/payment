package com.snowlightpay.membership.adapter.out.persistence;

import com.snowlightpay.membership.PersistenceAdapter;
import com.snowlightpay.membership.application.port.out.RegisterMembershipPort;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {
    private final MembershipRepository membershipRepository;

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
                                                                membershipCorp.isMembershipCorp()));
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
                                                Membership.MembershipCorp membershipCorp) {
        MembershipJpaEntity entity =
                membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()))
                                            .orElseThrow(IllegalArgumentException::new);

        entity.setName(membershipName.getMembershipName());
        entity.setEmail(membershipEmail.getMembershipEmail());
        entity.setAddress(membershipAddress.getMembershipAddress());
        entity.setValid(membershipValid.isMembershipValid());
        entity.setCorp(membershipCorp.isMembershipCorp());

        return membershipRepository.save(entity);
    }
}
