package com.snowlightpay.money.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    @Override
    public MoneyChangingRequest increaseMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        // ToDo MoneyChanging 비즈니스 로직 구현

        // 1. 고객 정보 확인. (맴버)
        // 2. 고객 계좌 확인. (뱅킹)
        // 3. 법인 계좌 확인. (뱅킹)

        // 4. 증액을 위한 *기록* 을 한다. (MoneyChangingRequest 저장)

        // 5. 고객 계좌에서 법인 계좌로 출금. (뱅킹)

        // 6-1. 성공하면 MoneyChangingRequest 업데이트) 리턴
        // 6-1-1 맴버의 실제 머니 충전

        // 6-2. 실패하면 MoneyChangingRequest 업데이트) 리턴

        return null;
    }
}
