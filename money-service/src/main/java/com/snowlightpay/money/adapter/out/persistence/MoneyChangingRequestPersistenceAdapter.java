package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.common.PersistenceAdapter;
import com.snowlightpay.money.application.port.out.IncreaseMoneyRequestPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyRequestPort {
}
