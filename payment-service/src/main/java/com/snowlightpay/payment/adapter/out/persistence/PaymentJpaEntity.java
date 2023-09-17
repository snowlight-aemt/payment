package com.snowlightpay.payment.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "payment")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class PaymentJpaEntity {
    @Id
    @GeneratedValue
    private Long paymentId;

    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;
    private LocalDateTime approvedAt;

    public PaymentJpaEntity(String requestMembershipId,
                            String requestPrice,
                            String franchiseId,
                            String franchiseFeeRate,
                            int paymentStatus,
                            LocalDateTime approvedAt) {
        this.requestMembershipId = requestMembershipId;
        this.requestPrice = requestPrice;
        this.franchiseId = franchiseId;
        this.franchiseFeeRate = franchiseFeeRate;
        this.paymentStatus = paymentStatus;
        this.approvedAt = approvedAt;
    }
}
