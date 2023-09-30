package com.snowlightpay.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
    List<MembershipJpaEntity> findByAddress(String membershipAddress);
}
