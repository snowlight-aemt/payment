package com.snowlightpay.remittance.adapter.out.persistance;

import com.snowlightpay.remittance.application.port.out.RemittanceRequestPort;
import com.snowlightpay.remittance.domain.Remittance;
import org.springframework.stereotype.Component;

@Component
public class RemittanceRequestPersistenceAdapter implements RemittanceRequestPort {
    private final RemittanceRequestRepository remittanceRequestRepository;

    public RemittanceRequestPersistenceAdapter(RemittanceRequestRepository remittanceRequestRepository) {
        this.remittanceRequestRepository = remittanceRequestRepository;
    }

    @Override
    public RemittanceRequestJpaEntity createRemittance(Remittance remittance) {
        return null;
    }

    @Override
    public RemittanceRequestJpaEntity saveRemittance(RemittanceRequestJpaEntity remittanceJpaEntity) {
        return null;
    }
}
