package com.snowlightpay.money.application.port.in;

import com.snowlightpay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyChangingRequest(IncreaseMoneyRequestCommand command);
}
