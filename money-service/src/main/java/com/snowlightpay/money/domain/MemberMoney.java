package com.snowlightpay.money.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class MemberMoney {
    private String memberMoneyId;
    private String membershipId;
    private int balance;


    public static MemberMoney generateMemberMoney(MemberMoneyId memberMoneyId,
                                                   MembershipId membershipId,
                                                   Balance balance) {
        return new MemberMoney(
            memberMoneyId.getMemberMoneyId(),
            membershipId.getMembershipId(),
            balance.getBalance()
        );
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(String memberMoneyId) { this.memberMoneyId = memberMoneyId; }
        String memberMoneyId;
    }
    @Value
    public static class MembershipId {
        public MembershipId(String membershipId) { this.membershipId = membershipId; }
        String membershipId;
    }
    @Value
    public static class Balance {
        public Balance(int balance) { this.balance = balance; }
        int balance;
    }

    @Value
    public static class MoneyAggregateIdentifier {
        public MoneyAggregateIdentifier(String aggregateIdentifier) { this.aggregateIdentifier = aggregateIdentifier; }
        String aggregateIdentifier;
    }
}
