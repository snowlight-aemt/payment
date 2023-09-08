package com.snowlightpay.money.application.port.out;

import com.snowlightpay.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {
    void createMemberMoney(
            MemberMoney.MembershipId membershipId,
            MemberMoney.MoneyAggregateIdentifier moneyAggregateIdentifier
    );
}
