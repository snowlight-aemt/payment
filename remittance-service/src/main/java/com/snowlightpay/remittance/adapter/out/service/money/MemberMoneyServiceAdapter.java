package com.snowlightpay.remittance.adapter.out.service.money;

import com.snowlightpay.remittance.application.port.out.MoneyPort;
import com.snowlightpay.remittance.application.port.out.MoneyResult;
import org.springframework.stereotype.Component;

@Component
public class MemberMoneyServiceAdapter implements MoneyPort {
    @Override
    public MemberMoney getMemberMoney() {
        return new MemberMoney("1", "1", 1000);
    }

    @Override
    public MoneyResult requestMoneyCharging() {
        return new MoneyResult("success");
    }

    @Override
    public boolean requestMoneyDecrease(String fromMembershipId, int amount) {
        return true;
    }

    @Override
    public boolean requestMoneyIncrease(String toMembershipId, int amount) {
        return true;
    }
}
