package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.banking.domain.RequestFirmBank;

public interface RequestFirmBankingUseCase {
    RequestFirmBank requestFirmBanking(RequestFirmBankingCommand command);

    void requestFirmBankingByEvent(RequestFirmBankingCommand command);
}
