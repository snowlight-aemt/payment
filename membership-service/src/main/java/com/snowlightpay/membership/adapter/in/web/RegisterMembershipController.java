package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.membership.application.port.in.RegisterMembershipCommand;
import com.snowlightpay.membership.application.port.in.RegisterMembershipUseCase;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {
    private final RegisterMembershipUseCase registerMembershipUseCase;
    @PostMapping("/membership/register")
    public ResponseEntity<Membership> registerMembership(@RequestBody RegisterMembershipRequest request) {
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
