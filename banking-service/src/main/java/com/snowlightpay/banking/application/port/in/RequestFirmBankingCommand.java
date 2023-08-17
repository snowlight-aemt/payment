package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

@Getter
public class RequestFirmBankingCommand extends SelfValidating<RequestFirmBankingCommand> {
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;

    public RequestFirmBankingCommand(
                                     String fromBankAccountNumber,
                                     String fromBankName,
                                     String toBankAccountNumber,
                                     String toBankName,
                                     int moneyAmount) {
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.fromBankName = fromBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.toBankName = toBankName;
        this.moneyAmount = moneyAmount;

        this.validateSelf();
    }
}
