package com.snowlightpay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyRequest {
    private String targetMembershipId;
    private int changingMoneyAmount;
}
