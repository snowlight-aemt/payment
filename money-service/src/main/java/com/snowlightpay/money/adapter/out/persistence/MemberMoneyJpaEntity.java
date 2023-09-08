package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.money.domain.MemberMoney;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Table(name = "member_money")
public class MemberMoneyJpaEntity {
    @GeneratedValue
    @Id
    private Long memberMoneyId;
    private Long membershipId;
    private int balance;
    private String aggregateIdentifier;

    public MemberMoneyJpaEntity(Long membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public String toString() {
        return "MemberMoneyJpaEntity{" +
                "memberMoneyId=" + memberMoneyId +
                ", membershipId=" + membershipId +
                ", balance=" + balance +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                '}';
    }

    public void increase(int balance) {
        this.balance = this.balance + balance;
    }
}
