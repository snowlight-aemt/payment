package com.snowlightpay.payment.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.payment.application.port.in.RequestPaymentCommand;
import com.snowlightpay.payment.application.port.in.RequestPaymentUseCase;
import com.snowlightpay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RequestPaymentController {
    private final RequestPaymentUseCase requestPaymentUseCase;

    @PostMapping("/payment/request")
    public ResponseEntity<Payment> payment(@Valid @RequestBody PaymentRequest request) {
        RequestPaymentCommand command = new RequestPaymentCommand(
                request.getRequestMembershipId(),
                request.getRequestPrice(),
                request.getRequestMembershipId(),
                request.getFranchiseFeeRate()
        );

        return ResponseEntity.ok(this.requestPaymentUseCase.payment(command));
    }

}
