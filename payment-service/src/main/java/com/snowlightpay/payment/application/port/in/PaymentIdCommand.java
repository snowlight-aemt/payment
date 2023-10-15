package com.snowlightpay.payment.application.port.in;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentIdCommand {
    private Long paymentId;
    public PaymentIdCommand(Long paymentId) {
        this.paymentId = paymentId;
    }
}