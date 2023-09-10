
package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.banking.domain.RequestFirmBank;

public interface UpdateFirmBankingUseCase {
    void updateFirmBanking(UpdateFirmBankingCommand command);
}
