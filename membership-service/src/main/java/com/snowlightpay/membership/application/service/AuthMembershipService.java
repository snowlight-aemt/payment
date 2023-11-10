package com.snowlightpay.membership.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.membership.application.port.in.AuthMembershipCommand;
import com.snowlightpay.membership.application.port.in.AuthMembershipUseCase;
import com.snowlightpay.membership.domain.JwtToken;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthMembershipService implements AuthMembershipUseCase {
    @Override
    public JwtToken login(AuthMembershipCommand command) {

    }
}
