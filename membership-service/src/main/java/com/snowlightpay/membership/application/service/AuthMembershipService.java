package com.snowlightpay.membership.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.membership.application.port.in.*;
import com.snowlightpay.membership.application.port.out.AuthMembershipPort;
import com.snowlightpay.membership.domain.JwtToken;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthMembershipService implements AuthMembershipUseCase {
    private final AuthMembershipPort authMembershipPort;
    private final FindMembershipService findMembershipService;
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @Override
    public JwtToken login(AuthMembershipCommand command) {
        String membershipId = command.getMembershipId();

        Membership membership =
                findMembershipService.findMembershipByMembershipId(new FindMembershipCommand(membershipId));


        if (membership.isValid()) {
            String jwtToken = authMembershipPort.generateJwtToken(new Membership.MembershipId(membershipId));
            String refreshToken = authMembershipPort.generateRefreshToken(new Membership.MembershipId(membershipId));

            modifyMembershipUseCase.modifyMembership(new ModifyMembershipCommand(
                    membership.getMembershipId(),
                    membership.getName(),
                    membership.getAddress(),
                    membership.getEmail(),
                    membership.isValid(),
                    membership.isCorp(),
                    refreshToken
            ));

            return JwtToken.generateJwtToken(new JwtToken.MembershipId(membershipId),
                                                new JwtToken.MembershipJwtToken(jwtToken),
                                                new JwtToken.MembershipRefreshToken(refreshToken));
        }

        return null;
    }
}
