package com.snowlightpay.payment.application.port.in;

import com.snowlightpay.payment.domain.Payment;

import java.util.List;

public interface RequestPaymentUseCase {
    public Payment payment(RequestPaymentCommand command);

    public List<Payment> getPaymentsByApprove();

    public Payment finishSettlement(PaymentIdCommand paymentIdCommand);
}
