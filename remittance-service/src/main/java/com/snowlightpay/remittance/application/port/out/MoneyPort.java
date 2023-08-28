package com.snowlightpay.remittance.application.port.out;

import com.snowlightpay.remittance.adapter.out.service.money.MemberMoney;

public interface MoneyPort {
    public MemberMoney getMemberMoney();

    MoneyResult requestMoneyCharging();

    boolean requestMoneyDecrease(String fromMembershipId, int amount);

    boolean requestMoneyIncrease(String toMembershipId, int amount);
}
