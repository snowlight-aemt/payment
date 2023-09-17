package com.snowlightpay.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Payment {
    private Long paymentId;
    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
    private int paymentStatus;              // 0 승인, 1 실패, 2 정산 완료
    private LocalDateTime approvedAt;

    public static Payment generatePayment(
            PaymentId paymentId,
            RequestMembershipId requestMembershipId,
            RequestPrice requestPrice,
            FranchiseId franchiseId,
            FranchiseFeeRate franchiseFeeRate,
            PaymentStatus paymentStatus,
            ApprovedAt approvedAt
    ) {
        return new Payment(
                paymentId.paymentId,
                requestMembershipId.getRequestMembershipId(),
                requestPrice.getRequestPrice(),
                franchiseId.getFranchiseId(),
                franchiseFeeRate.getFranchiseFeeRate(),
                paymentStatus.getPaymentStatus(),
                approvedAt.getApprovedAt());
    }

    @Value
    public static class PaymentId {
        public PaymentId(Long paymentId) {
            this.paymentId = paymentId;
        }

        Long paymentId;
    }
    @Value
    public static class RequestMembershipId {
        public RequestMembershipId(String requestMembershipId) {
            this.requestMembershipId = requestMembershipId;
        }

        String requestMembershipId;
    }
    @Value
    public static class RequestPrice {
        public RequestPrice(String requestPrice) {
            this.requestPrice = requestPrice;
        }

        String requestPrice;
    }
    @Value
    public static class FranchiseId {
        public FranchiseId(String franchiseId) {
            this.franchiseId = franchiseId;
        }

        String franchiseId;
    }
    @Value
    public static class FranchiseFeeRate {
        public FranchiseFeeRate(String franchiseFeeRate) {
            this.franchiseFeeRate = franchiseFeeRate;
        }

        String franchiseFeeRate;
    }
    @Value
    public static class PaymentStatus {
        public PaymentStatus(int paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        int paymentStatus;
    }
    @Value
    public static class ApprovedAt {
        public ApprovedAt(LocalDateTime approvedAt) {
            this.approvedAt = approvedAt;
        }

        LocalDateTime approvedAt;
    }
}
