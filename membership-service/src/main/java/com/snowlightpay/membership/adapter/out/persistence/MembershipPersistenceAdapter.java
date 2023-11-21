package com.snowlightpay.membership.adapter.out.persistence;

import com.snowlightpay.common.PersistenceAdapter;
import com.snowlightpay.membership.adapter.out.vault.VaultAdapter;
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

    private final VaultAdapter adapter;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                 Membership.MembershipEmail membershipEmail,
                                 Membership.MembershipAddress membershipAddress,
                                 Membership.MembershipValid membershipValid,
                                 Membership.MembershipCorp membershipCorp) {
        String encrypt = adapter.encrypt(membershipEmail.getMembershipEmail());
        MembershipJpaEntity entity = new MembershipJpaEntity(membershipName.getMembershipName(),
                encrypt,
                membershipAddress.getMembershipAddress(),
                membershipValid.isMembershipValid(),
                membershipCorp.isMembershipCorp(),
                membershipAddress.getMembershipAddress());
        membershipRepository.save(entity);

        MembershipJpaEntity clone = entity.clone();
        clone.setEmail(membershipEmail.getMembershipEmail());
        return clone;
    }

    @Override
    public MembershipJpaEntity findMemberByMembershipId(Membership.MembershipId membershipId) {
        MembershipJpaEntity membershipJpaEntity = membershipRepository.findById(Long.parseLong(membershipId.getMembershipId()))
                .orElseThrow(IllegalArgumentException::new);

        String encryptedMail = adapter.decrypt(membershipJpaEntity.getEmail());
        membershipJpaEntity.setEmail(encryptedMail);
        return membershipJpaEntity;
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
        List<Membership> collect = membershipRepository.findByAddress(membershipAddress.getMembershipAddress())
                .stream().map(v -> {
                    MembershipJpaEntity clone = v.clone();
                    String decrypt = adapter.decrypt(clone.getEmail());
                    clone.setEmail(decrypt);
                    return membershipMapper.mapToDomainEntity(clone);
        }).collect(Collectors.toList());

//        membershipMapper::mapToDomainEntity
        return collect;

    }
}
