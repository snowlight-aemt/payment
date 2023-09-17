package com.snowlightpay.payment.application.port.out;

import com.snowlightpay.payment.domain.Payment;

public interface CreatePaymentPort {

    Payment createPayment(Payment.RequestMembershipId requestMembershipId, Payment.RequestPrice requestPrice, Payment.FranchiseId franchiseId, Payment.FranchiseFeeRate franchiseFeeRate);
}
