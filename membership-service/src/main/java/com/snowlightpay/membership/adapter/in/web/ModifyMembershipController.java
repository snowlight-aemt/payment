package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.membership.application.port.in.ModifyMembershipCommand;
import com.snowlightpay.membership.application.port.in.ModifyMembershipUseCase;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping("/membership/modify/{membershipId}")
    public ResponseEntity<Membership> modifyMembershipByMembershipId(ModifyMembershipRequest modifyMembershipRequest) {
        ModifyMembershipCommand command = new ModifyMembershipCommand(modifyMembershipRequest.getMembershipId(),
                                                                        modifyMembershipRequest.getName(),
                                                                        modifyMembershipRequest.getAddress(),
                                                                        modifyMembershipRequest.getEmail(),
                                                                        modifyMembershipRequest.isValid(),
                                                                        modifyMembershipRequest.isCorp());
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }
}
