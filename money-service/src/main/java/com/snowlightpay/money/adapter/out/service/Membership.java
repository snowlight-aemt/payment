package com.snowlightpay.money.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;

    private final boolean isValid;
    private final boolean isCorp;
}
