package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.membership.application.port.in.*;
import com.snowlightpay.membership.domain.JwtToken;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/membership/validate-token")
    public ResponseEntity<Boolean> validateToken(ValidateJwtTokenRequest request) {
        ValidateTokenCommand command = new ValidateTokenCommand(request.getJwtToken());

        boolean isValid = authMembershipUseCase.validate(command);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/membership")
    public ResponseEntity<Membership> membership(JwtTokenRequest request) {

        Membership membership = authMembershipUseCase.membership(new JwtTokenCommand(request.getJwtToken()));

        return ResponseEntity.ok(membership);
    }
}
