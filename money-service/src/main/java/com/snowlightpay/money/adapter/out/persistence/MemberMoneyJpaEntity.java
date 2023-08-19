package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.money.domain.MemberMoney;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "member_money")
public class MemberMoneyJpaEntity {
    @GeneratedValue
    @Id
    private Long memberMoneyId;
    private Long membershipId;
    private int balance;

    public MemberMoneyJpaEntity(Long membershipId, int balance) {
        this.membershipId = membershipId;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MemberMoneyJpaEntity{" +
                "MemberMoneyId=" + memberMoneyId +
                ", MembershipId=" + membershipId +
                ", balance=" + balance +
                '}';
    }

    public void increase(int balance) {
        this.balance = this.balance + balance;
    }
}
