package com.snowlightpay.remittance.application.port.out;

public interface MembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
