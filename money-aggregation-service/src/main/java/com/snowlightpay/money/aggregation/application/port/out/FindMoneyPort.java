package com.snowlightpay.money.aggregation.application.port.out;

import java.util.List;

public interface FindMoneyPort {
    public List<MemberMoneyDto> findMoney(List<String> membershipIds);
}
