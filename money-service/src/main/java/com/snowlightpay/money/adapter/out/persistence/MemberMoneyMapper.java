package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.money.domain.MemberMoney;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberMoneyMapper {
    public MemberMoney mapToDomainEntity(MemberMoneyJpaEntity entity) {
        return MemberMoney.generateMemberMoney(
                new MemberMoney.MemberMoneyId(entity.getMemberMoneyId().toString()),
                new MemberMoney.MembershipId(entity.getMembershipId().toString()),
                new MemberMoney.Balance(entity.getBalance())
        );
    }
}
