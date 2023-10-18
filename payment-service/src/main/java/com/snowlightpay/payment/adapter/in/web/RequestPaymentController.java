package com.snowlightpay.payment.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.payment.application.port.in.PaymentIdCommand;
import com.snowlightpay.payment.application.port.in.RequestPaymentCommand;
import com.snowlightpay.payment.application.port.in.RequestPaymentUseCase;
import com.snowlightpay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

//    정상 결제 건들의 List
    @GetMapping("/payment/normal-status")
    public ResponseEntity<List<Payment>> getPayment() {
        return ResponseEntity.ok(this.requestPaymentUseCase.getPaymentsByApprove());
    }

//    각 결제 건들의 상태를 변경
    @PostMapping("/payment/finish-settlement")
    public ResponseEntity<Payment> updatePaymentStatus(@Valid @RequestBody PaymentIdRequest request) {
        PaymentIdCommand paymentIdCommand = new PaymentIdCommand(request.getPaymentId());
        return ResponseEntity.ok(this.requestPaymentUseCase.finishSettlement(paymentIdCommand));
    }

}
