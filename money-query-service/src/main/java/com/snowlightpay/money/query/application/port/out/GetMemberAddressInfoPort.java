package com.snowlightpay.money.query.application.port.out;

import java.util.Date;

public interface GetMemberAddressInfoPort {
    MemberAddressInfo getMemberAddressInfo(
            String membershipId
    );
}