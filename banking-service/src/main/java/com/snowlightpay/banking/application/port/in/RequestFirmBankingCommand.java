package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.common.SelfValidating;

public class RequestFirmBankingCommand extends SelfValidating<RequestFirmBankingCommand> {
    private String firmBankingId;
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;
    private int firmBankingStatus;

    public RequestFirmBankingCommand(String firmBankingId,
                                     String fromBankAccountNumber,
                                     String fromBankName,
                                     String toBankAccountNumber,
                                     String toBankName,
                                     int moneyAmount,
                                     int firmBankingStatus) {
        this.firmBankingId = firmBankingId;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.fromBankName = fromBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.toBankName = toBankName;
        this.moneyAmount = moneyAmount;
        this.firmBankingStatus = firmBankingStatus;

        this.validateSelf();
    }
}
