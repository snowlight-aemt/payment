package com.snowlightpay.banking.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "firm_banking")
public class RequestedFirmBankingJpaEntity {
    @GeneratedValue
    @Id
    private Long firmBankingId;
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;
    private int firmBankingStatus; // 0 요청 1 완료 2 실패
    private String uuid;

    public RequestedFirmBankingJpaEntity(String fromBankAccountNumber,
                                         String fromBankName,
                                         String toBankAccountNumber,
                                         String toBankName,
                                         int moneyAmount,
                                         int firmBankingStatus,
                                         UUID uuid) {
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.fromBankName = fromBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.toBankName = toBankName;
        this.moneyAmount = moneyAmount;
        this.firmBankingStatus = firmBankingStatus;
        this.uuid = uuid.toString();
    }

    @Override
    public String toString() {
        return "RequestedFirmBankingJpaEntity{" +
                "firmBankingId='" + firmBankingId + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                ", fromBankName='" + fromBankName + '\'' +
                ", toBankAccountNumber='" + toBankAccountNumber + '\'' +
                ", toBankName='" + toBankName + '\'' +
                ", moneyAmount=" + moneyAmount +
                ", firmBankingStatus=" + firmBankingStatus +
                '}';
    }
}
