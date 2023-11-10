package com.snowlightpay.membership.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtToken {
    private final String membershipId;
    private final String jwtToken;
    private final String refreshToken;

    public static JwtToken generateJwtToken (MembershipId membershipId,
                                           MembershipJwtToken membershipJwtToken,
                                           MembershipRefreshToken membershipRefreshToken) {
        return new JwtToken(membershipId.getMembershipId(),
                membershipJwtToken.getMembershipJwtToken(),
                membershipRefreshToken.getMembershipRefreshToken());
    }

    @Value
    public static class MembershipId {
        public MembershipId(String membershipId) { this.membershipId = membershipId; }
        String membershipId;
    }
    @Value
    public static class MembershipJwtToken {
        public MembershipJwtToken(String jwtToken) { this.membershipJwtToken = jwtToken; }
        String membershipJwtToken;
    }
    @Value
    public static class MembershipRefreshToken {
        public MembershipRefreshToken(String refreshToken) { this.membershipRefreshToken = refreshToken; }
        String membershipRefreshToken;
    }
}
