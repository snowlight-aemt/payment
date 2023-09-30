package com.snowlightpay.money.aggregation.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MembershipDto {
    private final String membershipId;
    private final String address;

    private final boolean isValid;
    private final boolean isCorp;
}
