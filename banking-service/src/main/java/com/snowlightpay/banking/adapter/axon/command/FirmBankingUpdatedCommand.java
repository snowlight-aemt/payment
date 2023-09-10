
package com.snowlightpay.banking.adapter.axon.command;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class FirmBankingUpdatedCommand extends SelfValidating<FirmBankingUpdatedCommand> {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    
    private String firmBankingId;
    private int status;

    public FirmBankingUpdatedCommand(String firmBankingId, String aggregateIdentifier, int status) {
        this.firmBankingId = firmBankingId;
        this.aggregateIdentifier = aggregateIdentifier;
        this.status = status;

        this.validateSelf();
    }
}
