package com.snowlightpay.remittance.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
}
