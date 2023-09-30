package com.snowlightpay.money.application.port.in;

import com.snowlightpay.money.domain.MemberMoney;

import java.util.List;

public interface FindMoneyUseCase {
    List<MemberMoney> findMemberMoneyListByMembershipIds(List<String> membershipIds);
}
