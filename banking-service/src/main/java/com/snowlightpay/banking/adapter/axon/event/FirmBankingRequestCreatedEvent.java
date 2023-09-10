package com.snowlightpay.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
public class FirmBankingRequestCreatedEvent {
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;

    public FirmBankingRequestCreatedEvent(String fromBankAccountNumber,
                                            String fromBankName,
                                            String toBankAccountNumber,
                                            String toBankName,
                                            int moneyAmount) {
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.fromBankName = fromBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.toBankName = toBankName;
        this.moneyAmount = moneyAmount;
    }

    public FirmBankingRequestCreatedEvent() {
    }
}
