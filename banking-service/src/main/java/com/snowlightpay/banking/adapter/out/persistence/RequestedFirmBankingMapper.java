package com.snowlightpay.banking.adapter.out.persistence;

import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.banking.domain.RequestFirmBank;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RequestedFirmBankingMapper {
    public RequestFirmBank mapToDomainEntity(RequestedFirmBankingJpaEntity entry, UUID uuid) {
        return RequestFirmBank.generateMember(
                new RequestFirmBank.FirmBankingId(entry.getFirmBankingId()+""),
                new RequestFirmBank.FromBankAccountNumber(entry.getFromBankAccountNumber()),
                new RequestFirmBank.FromBankName(entry.getFromBankName()),
                new RequestFirmBank.ToBankAccountNumber(entry.getToBankAccountNumber()),
                new RequestFirmBank.ToBankName(entry.getToBankName()),
                new RequestFirmBank.MoneyAmount(entry.getMoneyAmount()),
                new RequestFirmBank.FirmBankingStatus(entry.getFirmBankingStatus()),
                uuid
        );
    }
}
