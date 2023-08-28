package com.snowlightpay.remittance.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.remittance.application.port.in.RemittanceCommand;
import com.snowlightpay.remittance.application.port.in.RemittanceRequestUseCase;
import com.snowlightpay.remittance.domain.Remittance;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RemittanceRequestController {
    private final RemittanceRequestUseCase remittanceRequestUseCase;
    @PostMapping("/remittance/request")
    public ResponseEntity remittanceRequest(@RequestBody RemittanceRequest remittanceRequest) {
        RemittanceCommand command = new RemittanceCommand(
                remittanceRequest.getFromMembershipId(),
                remittanceRequest.getToMembershipId(),
                remittanceRequest.getToBankName(),
                remittanceRequest.getToBankAccountNumber(),
                remittanceRequest.getRemittanceType(),
                remittanceRequest.getAmount()
        );

        Remittance remittance = remittanceRequestUseCase.remittanceRequest(command);
        return ResponseEntity.ok(remittance);
    }
}
