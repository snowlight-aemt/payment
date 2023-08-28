package com.snowlightpay.remittance.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Remittance {
    private Long remittanceRequestId;
    private Long fromMembershipId;
    private Long toMembershipId;
    private String toBankAccountNumber;
    private String toBankName;
    private int remittanceType; // 0 membership, 1 backing
    private String remittanceStatus; // success fail
    private int amount;
    private String uuid;


    public static Remittance generateMember (RemittanceRequestId firmBankingId,
                                                FromMembershipId fromMembershipId,
                                                ToMembershipId toMembershipId,
                                                ToBankAccountNumber toBankAccountNumber,
                                                ToBankName toBankName,
                                                RemittanceType remittanceType,
                                                RemittanceStatus remittanceStatus,
                                                Amount amount,
                                                Uuid uuid) {
        return new Remittance(firmBankingId.getRemittanceRequestId(),
                                fromMembershipId.getFromMembershipId(),
                                toMembershipId.getToMembershipId(),
                                toBankAccountNumber.getToBankAccountNumber(),
                                toBankName.getToBankName(),
                                remittanceType.getRemittanceType(),
                                remittanceStatus.getRemittanceStatus(),
                                amount.getAmount(),
                                uuid.getUuid());
    }


    @Value
    public static class RemittanceRequestId {
        public RemittanceRequestId(Long remittanceRequestId) { this.remittanceRequestId = remittanceRequestId; }
        Long remittanceRequestId;
    }
    @Value
    public static class FromMembershipId {
        public FromMembershipId(Long fromMembershipId) { this.fromMembershipId = fromMembershipId; }
        Long fromMembershipId;
    }
    @Value
    public static class ToMembershipId {
        public ToMembershipId(Long toMembershipId) { this.toMembershipId = toMembershipId; }
        Long toMembershipId;
    }
    @Value
    public static class ToBankAccountNumber {
        public ToBankAccountNumber(String toBankAccountNumber) { this.toBankAccountNumber = toBankAccountNumber; }
        String toBankAccountNumber;
    }
    @Value
    public static class ToBankName {
        public ToBankName(String toBankName) { this.toBankName = toBankName; }
        String toBankName;
    }
    @Value
    public static class RemittanceType {
        public RemittanceType(int remittanceType) { this.remittanceType = remittanceType; }
        int remittanceType;
    }
    @Value
    public static class RemittanceStatus {
        public RemittanceStatus(String remittanceStatus) { this.remittanceStatus = remittanceStatus; }
        String remittanceStatus;
    }
    @Value
    public static class Amount {
        public Amount(int amount) { this.amount = amount; }
        int amount;
    }
    @Value
    public static class Uuid {
        public Uuid(String uuid) { this.uuid = uuid; }
        String uuid;
    }

}
