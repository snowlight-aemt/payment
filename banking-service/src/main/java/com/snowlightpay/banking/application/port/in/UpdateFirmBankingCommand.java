package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

@Getter
public class UpdateFirmBankingCommand extends SelfValidating<UpdateFirmBankingCommand> {
    private String firmBankingId;
    private int status;

    public UpdateFirmBankingCommand(String firmBankingId, int status) {
        this.firmBankingId = firmBankingId;
        this.status = status;

        this.validateSelf();
    }
}
