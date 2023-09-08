package com.snowlightpay.money.adapter.in.web;

import com.snowlightpay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingResultDetailMapper {
    public MoneyChangingResultDetail mapToMoneyChangingResultDetail(MoneyChangingRequest moneyChangingRequest) {
        return new MoneyChangingResultDetail(moneyChangingRequest.getMoneyChangingRequestId(),
                                                moneyChangingRequest.getChangingTypeStatus(),
                                                moneyChangingRequest.getMoneyChangingStatus(),
                                                moneyChangingRequest.getMoneyChangingAmount());
    }
}
