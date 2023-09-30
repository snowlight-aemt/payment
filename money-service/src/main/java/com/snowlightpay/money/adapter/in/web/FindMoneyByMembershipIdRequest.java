package com.snowlightpay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMoneyByMembershipIdRequest {
    private List<String> membershipIds;
}
