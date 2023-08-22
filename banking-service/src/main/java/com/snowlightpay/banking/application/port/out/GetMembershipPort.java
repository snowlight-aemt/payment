package com.snowlightpay.banking.application.port.out;

public interface GetMembershipPort {
    MembershipStatus getMembership(String membershipId);

}
