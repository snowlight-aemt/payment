package com.snowlightpay.remittance.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.remittance.adapter.out.service.money.MemberMoney;
import com.snowlightpay.remittance.adapter.out.persistance.RemittanceMapper;
import com.snowlightpay.remittance.adapter.out.persistance.RemittanceRequestJpaEntity;
import com.snowlightpay.remittance.application.port.in.RemittanceCommand;
import com.snowlightpay.remittance.application.port.in.RemittanceRequestUseCase;
import com.snowlightpay.remittance.application.port.out.*;
import com.snowlightpay.remittance.domain.Remittance;

@UseCase
public class RemittanceRequestService implements RemittanceRequestUseCase {
    private final RemittanceRequestPort remittanceRequestPort;
    private final RemittanceMapper remittanceMapper;
    private final MembershipPort membershipPort;
    private final MoneyPort moneyPort;
    private final RequestFirmBankingPort requestFirmBankingPort;

    public RemittanceRequestService(RemittanceRequestPort remittanceRequestPort,
                                    RemittanceMapper remittanceMapper,
                                    MembershipPort membershipPort,
                                    MoneyPort moneyPort,
                                    RequestFirmBankingPort requestFirmBankingPort) {
        this.remittanceRequestPort = remittanceRequestPort;
        this.remittanceMapper = remittanceMapper;
        this.membershipPort = membershipPort;
        this.moneyPort = moneyPort;
        this.requestFirmBankingPort = requestFirmBankingPort;
    }

    @Override
    public Remittance remittanceRequest(RemittanceCommand command) {
        // 0. 기록 (요청 상태)
        RemittanceRequestJpaEntity remittanceJpaEntity = remittanceRequestPort.createRemittance(null);

        // 1. Membership Check (맴버 여부 확인)
        MembershipStatus membershipStatus = this.membershipPort.getMembership(command.getFromMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

        // 2. Money Check (잔여 체크)
        MemberMoney memberMoney = this.moneyPort.getMemberMoney();
        if (memberMoney.getBalance() < command.getAmount()) {
            // 내 머니 충전
            MoneyResult moneyResult = this.moneyPort.requestMoneyCharging();
            if (moneyResult.equals("fail")) {
                return null;
            }
        }

        // 3. 송금
        if (command.getRemittanceType() == 0) {
        // 3-1. 맴버쉽
            boolean remittanceResultFromMoney = this.moneyPort.requestMoneyDecrease(command.getFromMembershipId(),
                                                                                        command.getAmount());
            boolean remittanceResultToMoney = this.moneyPort.requestMoneyIncrease(command.getToMembershipId(),
                                                                                    command.getAmount());

            if (!remittanceResultFromMoney ||!remittanceResultToMoney) {
                return null;
            }
        } else if (command.getRemittanceType() == 1) {
        // 3-2. 다른 뱅킹
            boolean remittanceResult = this.requestFirmBankingPort.requestFirmBanking();
            if (!remittanceResult) {
                return null;
            }
        }

        // 4. 기록 (성공 상태)
//        command.update();
        return remittanceMapper.mapToDomainEntity(remittanceRequestPort.saveRemittance(remittanceJpaEntity));
    }
}
