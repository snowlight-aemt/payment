package com.snowlightpay.membership.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMembershipRequest {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String address;

    private boolean isCorp;
}
