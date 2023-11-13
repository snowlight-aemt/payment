package com.snowlightpay.membership.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.snowlightpay.membership.adapter.out.persistence.MembershipMapper;
import com.snowlightpay.membership.application.port.in.*;
import com.snowlightpay.membership.application.port.out.AuthMembershipPort;
import com.snowlightpay.membership.application.port.out.RegisterMembershipPort;
import com.snowlightpay.membership.domain.JwtToken;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AuthMembershipService implements AuthMembershipUseCase {
    private final AuthMembershipPort authMembershipPort;
    private final RegisterMembershipPort registerMembershipPort;
    private final ModifyMembershipUseCase modifyMembershipUseCase;
    private final MembershipMapper membershipMapper;

    @Override
    public JwtToken login(AuthMembershipCommand command) {
        String membershipId = command.getMembershipId();
        MembershipJpaEntity memberByMembershipId =
                registerMembershipPort.findMemberByMembershipId(new Membership.MembershipId(membershipId));

        if (memberByMembershipId.isValid()) {
            String jwtToken = authMembershipPort.generateJwtToken(new Membership.MembershipId(membershipId));
            String refreshToken = authMembershipPort.generateRefreshToken(new Membership.MembershipId(membershipId));

            modifyMembershipUseCase.modifyMembership(new ModifyMembershipCommand(
                    membershipId,
                    memberByMembershipId.getName(),
                    memberByMembershipId.getAddress(),
                    memberByMembershipId.getEmail(),
                    memberByMembershipId.isValid(),
                    memberByMembershipId.isCorp(),
                    refreshToken
            ));

            return JwtToken.generateJwtToken(new JwtToken.MembershipId(membershipId),
                                                new JwtToken.MembershipJwtToken(jwtToken),
                                                new JwtToken.MembershipRefreshToken(refreshToken));
        }

        return null;
    }

    @Override
    public JwtToken refresh(RefreshTokenCommand command) {
        String refreshToken = command.getRefreshToken();
        boolean isValid = authMembershipPort.validateJwtToken(refreshToken);

        System.out.println(isValid);
        if (isValid) {
            Membership.MembershipId membershipId = authMembershipPort.parseMembershipIdFromToken(refreshToken);
            MembershipJpaEntity memberByMembershipId = registerMembershipPort.findMemberByMembershipId(membershipId);

            if (!memberByMembershipId.getRefreshToken().equals(refreshToken)) {
                return null;
            }

            if (memberByMembershipId.isValid()) {
                String jwtToken = authMembershipPort.generateJwtToken(membershipId);
                // membershipId, jwtToken, refreshToken
                return JwtToken.generateJwtToken(new JwtToken.MembershipId(membershipId.getMembershipId()),
                        new JwtToken.MembershipJwtToken(jwtToken),
                        new JwtToken.MembershipRefreshToken(refreshToken));
            }
        }
        return null;
    }

    @Override
    public boolean validate(ValidateTokenCommand command) {
        return authMembershipPort.validateJwtToken(command.getJwtToken());
    }

    @Override
    public Membership membership(JwtTokenCommand command) {
        String jwtToken = command.getJwtToken();

        boolean isValid = authMembershipPort.validateJwtToken(jwtToken);
        if (isValid) {
            Membership.MembershipId membershipId = authMembershipPort.parseMembershipIdFromToken(jwtToken);
            MembershipJpaEntity membershipJpaEntity = registerMembershipPort.findMemberByMembershipId(membershipId);

            return membershipMapper.mapToDomainEntity(membershipJpaEntity);
        }

        return null;
    }
}
