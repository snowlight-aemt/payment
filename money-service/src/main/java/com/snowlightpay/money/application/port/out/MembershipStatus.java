package com.snowlightpay.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}
