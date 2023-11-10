package com.snowlightpay.membership.application.port.out;

import com.snowlightpay.membership.domain.Membership;

public interface AuthMembershipPort {
    String generateJwtToken(Membership.MembershipId membershipId);
    String generateRefreshToken(Membership.MembershipId membershipId);
    boolean validateJwtToken(String jwtToken);
    Membership.MembershipId parseMembershipIdFromToken(String jwtToken);
}
