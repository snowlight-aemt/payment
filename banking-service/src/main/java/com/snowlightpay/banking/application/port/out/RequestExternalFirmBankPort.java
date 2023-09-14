package com.snowlightpay.banking.application.port.out;

import com.snowlightpay.banking.adapter.out.external.bank.FirmBankingResult;
import com.snowlightpay.banking.domain.RequestFirmBank;

public interface RequestExternalFirmBankPort {
    FirmBankingResult requestFirmBanking(RequestFirmBank.FromBankName fromBankName,
                                         RequestFirmBank.FromBankAccountNumber fromBankAccountNumber,
                                         RequestFirmBank.ToBankName toBankName,
                                         RequestFirmBank.ToBankAccountNumber toBankAccountNumber,
                                         RequestFirmBank.MoneyAmount moneyAmount);
}
