package com.snowlightpay.banking.application.port.out;

import com.snowlightpay.banking.adapter.out.persistence.RequestedFirmBankingJpaEntity;
import com.snowlightpay.banking.domain.RequestFirmBank;

import java.util.UUID;

public interface RequestFirmBankPort {
    RequestedFirmBankingJpaEntity createFirmBanking(RequestFirmBank.FromBankName fromBankName,
                                                    RequestFirmBank.FromBankAccountNumber fromBankAccountNumber,
                                                    RequestFirmBank.ToBankName toBankName,
                                                    RequestFirmBank.ToBankAccountNumber toBankAccountNumber,
                                                    RequestFirmBank.MoneyAmount moneyAmount,
                                                    RequestFirmBank.FirmBankingStatus firmBankingStatus);
    RequestedFirmBankingJpaEntity modifyFirmBanking(RequestedFirmBankingJpaEntity requestedFirmBankingJpaEntity);
}
