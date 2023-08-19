package com.snowlightpay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    private String moneyChangingRequestId;
    private String targetMembershipId;
    private int moneyChangingAmount;
    private int moneyChangingStatus; // enum. 0 요청, 1 성공, 2 실패
    private int changingTypeStatus; // enum. 0 증액, 1 감액
    private UUID uuid;

    public static MoneyChangingRequest generateMember (
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            MoneyChangingAmount moneyChangingAmount,
            MoneyChangingStatus moneyChangingStatus,
            ChangingType changingType,
            Uuid uuid
    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMembershipId.getTargetMembershipId(),
                moneyChangingAmount.getMoneyChangingAmount(),
                moneyChangingStatus.getMoneyChangingStatus(),
                changingType.getChangingType(),
                uuid.getUuid()
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
    @Value
    public static class Uuid {
        public Uuid(UUID uuid) { this.uuid = uuid; }
        UUID uuid;
    }
}
