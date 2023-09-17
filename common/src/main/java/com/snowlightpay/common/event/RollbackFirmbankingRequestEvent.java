package com.snowlightpay.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingRequestEvent {
    private String rollbackFirmbankingId;
    private String membershipId;
    private String id;
}
