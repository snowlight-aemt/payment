package com.snowlightpay.money.aggregation.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberMoneyDto {
    private String memberMoneyId;
    private String membershipId;
    private int balance;
}
