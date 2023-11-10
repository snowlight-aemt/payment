package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.membership.domain.JwtToken;

public interface AuthMembershipUseCase {
    JwtToken login(AuthMembershipCommand command);

    JwtToken refresh(RefreshTokenCommand command);
}
