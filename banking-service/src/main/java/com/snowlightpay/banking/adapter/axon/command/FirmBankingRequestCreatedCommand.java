package com.snowlightpay.banking.adapter.axon.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FirmBankingRequestCreatedCommand {
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;

    public FirmBankingRequestCreatedCommand(String fromBankAccountNumber,
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

    public FirmBankingRequestCreatedCommand() {
    }
}
