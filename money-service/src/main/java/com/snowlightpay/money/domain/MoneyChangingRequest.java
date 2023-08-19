package com.snowlightpay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    private String moneyChangingRequestId;
    private String targetMembershipId;
    private int moneyChangingAmount;
    private int moneyChangingStatus;
    private int changingTypeStatus;

    public static MoneyChangingRequest generateMember (
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            MoneyChangingAmount moneyChangingAmount,
            MoneyChangingStatus moneyChangingStatus,
            ChangingType changingType
    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMembershipId.getTargetMembershipId(),
                moneyChangingAmount.getMoneyChangingAmount(),
                moneyChangingStatus.getMoneyChangingStatus(),
                changingType.getChangingType()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String moneyChangingRequestId) { this.moneyChangingRequestId = moneyChangingRequestId; }
        String moneyChangingRequestId;
    }
    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(String targetMembershipId) { this.targetMembershipId = targetMembershipId; }
        String targetMembershipId;
    }
    @Value
    public static class MoneyChangingAmount {
        public MoneyChangingAmount(int moneyChangingAmount) { this.moneyChangingAmount = moneyChangingAmount; }
        int moneyChangingAmount;
    }
    @Value
    public static class MoneyChangingStatus {
        public MoneyChangingStatus(int moneyChangingStatus) { this.moneyChangingStatus = moneyChangingStatus; }
        int moneyChangingStatus;
    }
    @Value
    public static class ChangingType {
        public ChangingType(int changingType) { this.changingType = changingType; }
        int changingType;
    }
}
