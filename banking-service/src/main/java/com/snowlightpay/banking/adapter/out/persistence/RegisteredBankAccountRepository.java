package com.snowlightpay.banking.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    List<RegisteredBankAccountJpaEntity> findByMembershipId(long membershipId);
}
