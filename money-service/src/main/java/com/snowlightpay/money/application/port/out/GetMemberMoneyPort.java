package com.snowlightpay.money.application.port.out;

import com.snowlightpay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.snowlightpay.money.domain.MemberMoney;

import java.util.List;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId memberMoneyId);

    List<MemberMoney> findMemberMoneyListByMembershipIds(List<MemberMoney.MembershipId> membershipIds);
}
