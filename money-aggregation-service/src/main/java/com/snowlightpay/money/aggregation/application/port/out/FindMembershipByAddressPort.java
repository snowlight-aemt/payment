package com.snowlightpay.money.aggregation.application.port.out;

import com.snowlightpay.money.aggregation.domain.MoneySum;

import java.util.List;

public interface FindMembershipByAddressPort {
    public List<MembershipDto> findMembershipByAddress(String address);
}
