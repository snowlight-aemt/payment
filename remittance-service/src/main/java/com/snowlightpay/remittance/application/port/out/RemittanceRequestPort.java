package com.snowlightpay.remittance.application.port.out;

import com.snowlightpay.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.snowlightpay.remittance.domain.Remittance;

public interface RemittanceRequestPort {
    public RemittanceRequestJpaEntity createRemittance(Remittance remittance);

    RemittanceRequestJpaEntity saveRemittance(RemittanceRequestJpaEntity remittanceJpaEntity);
}
