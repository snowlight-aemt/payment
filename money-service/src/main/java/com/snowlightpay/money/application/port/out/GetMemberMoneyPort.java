package com.snowlightpay.money.application.port.out;

import com.snowlightpay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.snowlightpay.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId memberMoneyId);
}
