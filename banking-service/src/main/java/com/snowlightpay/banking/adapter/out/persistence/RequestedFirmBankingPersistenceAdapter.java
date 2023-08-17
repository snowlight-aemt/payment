package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.application.port.out.RegisterBankAccountPort;
import com.snowlightpay.banking.application.port.out.RequestFirmBankPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestedFirmBankingPersistenceAdapter implements RequestFirmBankPort {
    @Override
    public void createFirmBanking() {

    }
}
