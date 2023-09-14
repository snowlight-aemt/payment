package com.snowlightpay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredBankAccountEvent {
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private boolean isChecked;

    private int amount;
    private String firmbankingRequestAggregateIdentifier;

    private String fromBankName;
    private String fromBankAccountNumber;

    @Override
    public String toString() {
        return "CheckRegisteredBankAccountEvent{" +
                "rechargingRequestId='" + rechargingRequestId + '\'' +
                ", checkRegisteredBankAccountId='" + checkRegisteredBankAccountId + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", isChecked=" + isChecked +
                ", amount=" + amount +
                ", firmbankingRequestAggregateIdentifier='" + firmbankingRequestAggregateIdentifier + '\'' +
                ", fromBankName='" + fromBankName + '\'' +
                ", fromBankAccountNumber='" + fromBankAccountNumber + '\'' +
                '}';
    }
}
