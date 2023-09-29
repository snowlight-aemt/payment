package com.snowlightpay.membership.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.membership.application.port.in.FindMembershipByAddressCommand;
import com.snowlightpay.membership.application.port.in.FindMembershipCommand;
import com.snowlightpay.membership.application.port.in.FindMembershipUseCase;
import com.snowlightpay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping("/membership/{membershipId}")
    public ResponseEntity<Membership> findMembershipByMembershipId(@PathVariable String membershipId) {
        FindMembershipCommand command = new FindMembershipCommand(membershipId);
        Membership membership = findMembershipUseCase.findMembershipByMembershipId(command);

        return ResponseEntity.ok(membership);
    }

    @GetMapping("/membership/address")
    public ResponseEntity<List<Membership>> findMembershipListByAddress(
            @ParameterObject @ModelAttribute FindMembershipByAddressRequest request) {
        FindMembershipByAddressCommand command = new FindMembershipByAddressCommand(request.getAddress());
        return ResponseEntity.ok(findMembershipUseCase.findMembershipByAddress(command));
    }
}
