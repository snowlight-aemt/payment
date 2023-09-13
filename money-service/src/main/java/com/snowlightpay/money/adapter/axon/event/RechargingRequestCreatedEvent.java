package com.snowlightpay.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargingRequestCreatedEvent {
    // "충전" 이벤트

    private String rechargingRequestId;
    private String membershipId;
    private int amount;
}
