package com.snowlightpay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestFirmBank {
    private String firmBankingId;
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;
    private int firmBankingStatus; // 0 요청 1 완료 2 실패
    private UUID uuid;


    public static RequestFirmBank generateMember (FirmBankingId firmBankingId,
                                                  FromBankAccountNumber fromBankAccountNumber,
                                                  FromBankName fromBankName,
                                                  ToBankAccountNumber toBankAccountNumber,
                                                  ToBankName toBankName,
                                                  MoneyAmount moneyAmount,
                                                  FirmBankingStatus firmBankingStatus,
                                                  UUID uuid) {
        return new RequestFirmBank(
                firmBankingId.getFirmBankingId(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                fromBankName.getFromBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                toBankName.getToBankName(),
                moneyAmount.getMoneyAmount(),
                firmBankingStatus.getFirmBankingStatus(),
                uuid
        );
    }


    @Value
    public static class FirmBankingId {
        public FirmBankingId(String firmBankingId) { this.firmBankingId = firmBankingId; }
        String firmBankingId;
    }
    @Value
    public static class FromBankAccountNumber {
        public FromBankAccountNumber(String fromBankAccountNumber) { this.fromBankAccountNumber = fromBankAccountNumber; }
        String fromBankAccountNumber;
    }
    @Value
    public static class FromBankName {
        public FromBankName(String fromBankName) { this.fromBankName = fromBankName; }
        String fromBankName;
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
    public static class MoneyAmount {
        public MoneyAmount(int moneyAmount) { this.moneyAmount = moneyAmount; }
        int moneyAmount;
    }
    @Value
    public static class FirmBankingStatus {
        public FirmBankingStatus(int firmBankingStatus) { this.firmBankingStatus = firmBankingStatus; }
        int firmBankingStatus;
    }
}
