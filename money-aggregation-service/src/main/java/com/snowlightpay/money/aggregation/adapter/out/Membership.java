package com.snowlightpay.money.aggregation.adapter.out;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    private final String membershipId;
    private final String name;
    private final String email;
    private final String address;

    private final boolean isValid;
    private final boolean isCorp;

    public static Membership generateMember (MembershipId membershipId,
                                             MembershipName membershipName,
                                             MembershipEmail membershipEmail,
                                             MembershipAddress membershipAddress,
                                             MembershipValid membershipValid,
                                             MembershipCorp membershipCorp) {
        return new Membership(membershipId.getMembershipId(),
                membershipName.getMembershipName(),
                membershipEmail.getMembershipEmail(),
                membershipAddress.getMembershipAddress(),
                membershipValid.isMembershipValid(),
                membershipCorp.isMembershipCorp());
    }

    @Value
    public static class MembershipId {
        public MembershipId(String membershipId) { this.membershipId = membershipId; }
        String membershipId;
    }
    @Value
    public static class MembershipName {
        public MembershipName(String membershipName) { this.membershipName = membershipName; }
        String membershipName;
    }
    @Value
    public static class MembershipEmail {
        public MembershipEmail(String membershipEmail) { this.membershipEmail = membershipEmail; }
        String membershipEmail;
    }
    @Value
    public static class MembershipAddress {
        public MembershipAddress(String membershipAddress) { this.membershipAddress = membershipAddress; }
        String membershipAddress;
    }
    @Value
    public static class MembershipValid {
        public MembershipValid(boolean membershipValid) { this.membershipValid = membershipValid; }
        boolean membershipValid;
    }
    @Value
    public static class MembershipCorp {
        public MembershipCorp(boolean membershipCorp) { this.membershipCorp = membershipCorp; }
        boolean membershipCorp;
    }
}
