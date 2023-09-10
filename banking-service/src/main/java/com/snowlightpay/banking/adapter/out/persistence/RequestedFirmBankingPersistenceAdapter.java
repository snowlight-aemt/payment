package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.application.port.out.RequestFirmBankPort;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class RequestedFirmBankingPersistenceAdapter implements RequestFirmBankPort {
    private final RequestedFirmBankingRepository requestedFirmBankingRepository;
    @Override
    public RequestedFirmBankingJpaEntity createFirmBanking(RequestFirmBank.FromBankName fromBankName,
                                                            RequestFirmBank.FromBankAccountNumber fromBankAccountNumber,
                                                            RequestFirmBank.ToBankName toBankName,
                                                            RequestFirmBank.ToBankAccountNumber toBankAccountNumber,
                                                            RequestFirmBank.MoneyAmount moneyAmount,
                                                            RequestFirmBank.FirmBankingStatus firmBankingStatus,
                                                            RequestFirmBank.AggregateIdentifier aggregateIdentifier) {
        return this.requestedFirmBankingRepository.save(new RequestedFirmBankingJpaEntity(
                fromBankAccountNumber.getFromBankAccountNumber(),
                fromBankName.getFromBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                toBankName.getToBankName(),
                moneyAmount.getMoneyAmount(),
                firmBankingStatus.getFirmBankingStatus(),
                UUID.randomUUID(),
                aggregateIdentifier.getAggregateIdentifier()
        ));
    }

    @Override
    public RequestedFirmBankingJpaEntity getFirmBanking(RequestFirmBank.FirmBankingId firmBankingId) {
        return requestedFirmBankingRepository.findById(Long.parseLong(firmBankingId.getFirmBankingId()))
                                                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public RequestedFirmBankingJpaEntity modifyFirmBanking(RequestedFirmBankingJpaEntity requestedFirmBankingJpaEntity) {
        return this.requestedFirmBankingRepository.save(requestedFirmBankingJpaEntity);
    }
}
