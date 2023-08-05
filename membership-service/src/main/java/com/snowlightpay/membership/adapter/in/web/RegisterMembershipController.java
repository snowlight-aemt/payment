package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.membership.WebAdapter;
import com.snowlightpay.membership.application.port.in.RegisterMembershipCommand;
import com.snowlightpay.membership.application.port.in.RegisterMembershipUseCase;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;
    @PostMapping("/membership/register")
    public ResponseEntity<Membership> hello(@RequestBody RegisterMembershipRequest request) {
        // request -> command
        RegisterMembershipCommand command = new RegisterMembershipCommand(request.getName(),
                request.getEmail(),
                request.getAddress(),
                true,
                request.isCorp());

        // port(useCase)
        Membership membership = registerMembershipUseCase.createMembership(command);
        return ResponseEntity.ok(membership);
    }
}
