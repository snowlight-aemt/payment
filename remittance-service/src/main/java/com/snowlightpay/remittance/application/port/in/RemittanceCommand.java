package com.snowlightpay.remittance.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Data;

@Data
public class RemittanceCommand extends SelfValidating<RemittanceCommand> {
    private final String fromMembershipId;
    private final String toMembershipId;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final int remittanceType;  // 0 membershipid, 1 banking
    private final int amount;

    public RemittanceCommand(String fromMembershipId,
                                String toMembershipId,
                                String toBankName,
                                String toBankAccountNumber,
                                int remittanceType,
                                int amount) {
        this.fromMembershipId = fromMembershipId;
        this.toMembershipId = toMembershipId;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.remittanceType = remittanceType;
        this.amount = amount;

        this.validateSelf();
    }
}
