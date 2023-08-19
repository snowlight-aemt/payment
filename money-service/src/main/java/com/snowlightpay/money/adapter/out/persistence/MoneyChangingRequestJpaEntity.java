package com.snowlightpay.money.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "money_changing_reqest")
public class MoneyChangingRequestJpaEntity {
    @GeneratedValue
    @Id
    private Long moneyChangingRequestId;
    private Long targetMembershipId;
    private int moneyChangingAmount;
    private int changingType;
    private int moneyChangingStatus;
    private LocalDate createAt;

    public MoneyChangingRequestJpaEntity(Long targetMembershipId,
                                         int moneyChangingAmount,
                                         int changingType,
                                         int moneyChangingStatus,
                                         LocalDate createAt) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingAmount = moneyChangingAmount;
        this.changingType = changingType;
        this.moneyChangingStatus = moneyChangingStatus;
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "MoneyChangingRequestJpaEntity{" +
                "moneyChangingRequestId=" + moneyChangingRequestId +
                ", targetMembershipId=" + targetMembershipId +
                ", moneyChangingAmount=" + moneyChangingAmount +
                ", changingType=" + changingType +
                ", moneyChangingStatus=" + moneyChangingStatus +
                ", createAt=" + createAt +
                '}';
    }
}
