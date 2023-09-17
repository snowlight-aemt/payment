package com.snowlightpay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingRequestCommand {
    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String requestFirmbankingAggregateIdentifier;
    private String rechargingRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;
}
