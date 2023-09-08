package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.money.domain.MemberMoney;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    List<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);
}
