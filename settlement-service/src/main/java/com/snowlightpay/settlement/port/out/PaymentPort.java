package com.snowlightpay.settlement.port.out;

import com.snowlightpay.settlement.adapter.out.service.Payment;
import com.snowlightpay.settlement.adapter.out.service.PaymentIdRequest;
import com.snowlightpay.settlement.adapter.out.service.PaymentRequest;

import java.util.List;

public interface PaymentPort {
    public List<Payment> payment();
    public Payment updatePaymentStatus(PaymentIdRequest request);
}
