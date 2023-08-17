package com.snowlightpay.banking.application.service;

import com.snowlightpay.banking.adapter.out.external.bank.FirmBankingResult;
import com.snowlightpay.banking.adapter.out.persistence.RequestedFirmBankingJpaEntity;
import com.snowlightpay.banking.adapter.out.persistence.RequestedFirmBankingMapper;
import com.snowlightpay.banking.application.port.in.RequestFirmBankingCommand;
import com.snowlightpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.snowlightpay.banking.application.port.out.RequestExternalFirmBankPort;
import com.snowlightpay.banking.application.port.out.RequestFirmBankPort;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class RequestFirmBankingService implements RequestFirmBankingUseCase {
    private final RequestFirmBankPort requestFirmBankPort;
    private final RequestExternalFirmBankPort requestExtelnalPort;
    private final RequestedFirmBankingMapper requestedFirmBankingMapper;
    @Override
    public RequestFirmBank requestFirmBanking(RequestFirmBankingCommand command) {

        // 1. 요청에 대해 정보를 먼저 체크 (요청)
        RequestedFirmBankingJpaEntity entity = this.requestFirmBankPort.createFirmBanking(
                new RequestFirmBank.FromBankName(command.getFromBankName()),
                new RequestFirmBank.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmBank.ToBankName(command.getToBankName()),
                new RequestFirmBank.ToBankAccountNumber(command.getToBankAccountNumber()),
                new RequestFirmBank.MoneyAmount(command.getMoneyAmount()),
                new RequestFirmBank.FirmBankingStatus(0)
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmBankingResult firmBankingResult = this.requestExtelnalPort.requestFirmBanking(
                new RequestFirmBank.FromBankName(command.getFromBankName()),
                new RequestFirmBank.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new RequestFirmBank.ToBankName(command.getToBankName()),
                new RequestFirmBank.ToBankAccountNumber(command.getToBankAccountNumber())
        );

        UUID uuid = UUID.randomUUID();
        entity.setUuid(uuid.toString());
        // 3. 결과에 따라서 1번 Update
        if (firmBankingResult.getResultCode() == 0) {
            // 성공
            entity.setFirmBankingStatus(1);
        } else {
            // 실패
            entity.setFirmBankingStatus(2);
        }

        // 4. 결과 리턴
        return requestedFirmBankingMapper.mapToDomainEntity(requestFirmBankPort.modifyFirmBanking(entity), uuid);
    }
}
