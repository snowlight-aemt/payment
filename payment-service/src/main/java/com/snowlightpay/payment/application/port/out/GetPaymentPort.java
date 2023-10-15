package com.snowlightpay.payment.application.port.out;

import com.snowlightpay.payment.domain.Payment;

import java.util.List;

public interface GetPaymentPort {
    List<Payment> getPayment();

    Payment updatePaymentBy(Long franchiseId, int status);
}
