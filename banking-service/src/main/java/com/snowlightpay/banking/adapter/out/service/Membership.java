package com.snowlightpay.banking.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
