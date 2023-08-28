package com.snowlightpay.remittance.application.port.in;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.remittance.domain.Remittance;

@UseCase
public interface RemittanceRequestUseCase {
    public Remittance remittanceRequest(RemittanceCommand remittanceCommand);
}
