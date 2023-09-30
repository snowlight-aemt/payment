package com.snowlightpay.membership.adapter.in.web;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindMembershipByAddressRequest {
    @NotNull
    private String address;
}
