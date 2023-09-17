package com.snowlightpay.payment.application.port.in;

import com.snowlightpay.payment.domain.Payment;

public interface RequestPaymentUseCase {
    public Payment payment(RequestPaymentCommand command);
}
