package com.snowlightpay.money.application.port.out;

import com.snowlightpay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.snowlightpay.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestPort {

    MoneyChangingRequestJpaEntity createMoneyChanging(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingRequest.MoneyChangingAmount moneyChangingAmount, MoneyChangingRequest.ChangingType changingType, MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus);
}
