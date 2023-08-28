package com.snowlightpay.remittance.adapter.out.service.money;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberMoney {
    private String memberMoneyId;
    private String membershipId;
    private int balance;
}
