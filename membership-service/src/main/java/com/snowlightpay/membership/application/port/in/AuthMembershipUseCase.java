package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.membership.domain.JwtToken;
import com.snowlightpay.membership.domain.Membership;

public interface AuthMembershipUseCase {
    JwtToken login(AuthMembershipCommand command);

    JwtToken refresh(RefreshTokenCommand command);

    boolean validate(ValidateTokenCommand command);
    Membership membership(JwtTokenCommand command);
}
