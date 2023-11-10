package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.membership.application.port.in.AuthMembershipCommand;
import com.snowlightpay.membership.application.port.in.AuthMembershipUseCase;
import com.snowlightpay.membership.application.port.in.RefreshTokenCommand;
import com.snowlightpay.membership.domain.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class AuthMembershipController {

    private final AuthMembershipUseCase authMembershipUseCase;

    @PostMapping("/membership/login")
    public ResponseEntity<JwtToken> loginMembership(AuthMembershipRequest request) {
        AuthMembershipCommand command = new AuthMembershipCommand(request.getMembershipId());
        JwtToken jwtToken = authMembershipUseCase.login(command);

        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/membership/refresh-token")
    public ResponseEntity<JwtToken> refreshMembership(RefreshTokenRequest request) {
        RefreshTokenCommand command = new RefreshTokenCommand(request.getRefreshToken());
        JwtToken refresh = authMembershipUseCase.refresh(command);

        return ResponseEntity.ok(refresh);
    }
}
