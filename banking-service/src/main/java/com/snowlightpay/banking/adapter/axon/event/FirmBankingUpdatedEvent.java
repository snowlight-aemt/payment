
package com.snowlightpay.banking.adapter.axon.event;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

@Getter
public class FirmBankingUpdatedEvent extends SelfValidating<FirmBankingUpdatedEvent> {
    private String firmBankingId;
    private String aggregateIdentifier;
    private int status;

    public FirmBankingUpdatedEvent(String firmBankingId, String aggregateIdentifier, int status) {
        this.firmBankingId = firmBankingId;
        this.aggregateIdentifier = aggregateIdentifier;
        this.status = status;

        this.validateSelf();
    }
}
