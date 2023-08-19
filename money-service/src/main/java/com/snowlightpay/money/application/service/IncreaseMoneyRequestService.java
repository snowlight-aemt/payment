package com.snowlightpay.money.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.snowlightpay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.snowlightpay.money.application.port.out.IncreaseMoneyRequestPort;
import com.snowlightpay.money.domain.MemberMoney;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    private final IncreaseMoneyRequestPort increaseMoneyRequestPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;
    @Override
    public MoneyChangingRequest increaseMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        // ToDo MoneyChanging 비즈니스 로직 구현

        // 1. 고객 정보 확인. (맴버)
        // 2. 고객 계좌 확인. (뱅킹)
        // 3. 법인 계좌 확인. (뱅킹)

        // 4. 증액을 위한 *기록* 을 한다. (MoneyChangingRequest 저장)
        MoneyChangingRequestJpaEntity moneyChanging = this.increaseMoneyRequestPort.createMoneyChanging(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.MoneyChangingAmount(command.getMoneyChangingAmount()),
                new MoneyChangingRequest.ChangingType(0),
                new MoneyChangingRequest.MoneyChangingStatus(0)
        );

        // 5. 고객 계좌에서 법인 계좌로 출금. (뱅킹)

        // 6-1. 성공하면 MoneyChangingRequest 업데이트) 리턴
        // 6-1-1 맴버의 실제 머니 충전
        moneyChanging.moneyChangingSuccess();
        // ToDo 더티 체크로 업데이트 되는데?? 필요한가??
//        moneyChanging = this.increaseMoneyRequestPort.modifyMoneyChanging(moneyChanging);

        if (moneyChanging.getMoneyChangingStatus() == 1) {
            this.increaseMoneyRequestPort.increaseMemberMoney(
                    new MemberMoney.MembershipId(moneyChanging.getTargetMembershipId()+""),
                    new MemberMoney.Balance(moneyChanging.getMoneyChangingAmount()));
        }

        return this.moneyChangingRequestMapper.mapToDomainEntity(moneyChanging);
        // 6-2. 실패하면 MoneyChangingRequest 업데이트) 리턴

    }
}
