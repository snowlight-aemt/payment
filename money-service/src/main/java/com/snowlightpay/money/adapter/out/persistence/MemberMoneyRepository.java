package com.snowlightpay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    List<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);

    @Query("select m from MemberMoneyJpaEntity m where m.membershipId in (:membershipIds)")
    List<MemberMoneyJpaEntity> findBalanceByMembershipIds(List<Long> membershipIds);
}
