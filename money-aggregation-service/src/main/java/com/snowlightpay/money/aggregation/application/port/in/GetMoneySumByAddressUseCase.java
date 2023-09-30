package com.snowlightpay.money.aggregation.application.port.in;

import com.snowlightpay.money.aggregation.domain.MoneySum;

public interface GetMoneySumByAddressUseCase {
    public MoneySum getMoneySumByAddress(GetMoneySumByAddressCommand command);
}
