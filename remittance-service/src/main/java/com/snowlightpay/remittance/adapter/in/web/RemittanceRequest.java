package com.snowlightpay.remittance.adapter.in.web;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RemittanceRequest {
    @NotNull
    private String fromMembershipId;

    private String toMembershipId;

    private String toBankAccountNumber;
    private String toBankName;

    private int remittanceType; // 0 membershipid, 1 banking

    private int amount;
}
