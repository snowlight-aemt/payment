package com.snowlightpay.payment.adapter.out.persistence;

import com.snowlightpay.payment.application.port.out.CreatePaymentPort;
import com.snowlightpay.payment.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PaymentPersistenceAdapter implements CreatePaymentPort {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public Payment createPayment(Payment.RequestMembershipId requestMembershipId,
                                 Payment.RequestPrice requestPrice,
                                 Payment.FranchiseId franchiseId,
                                 Payment.FranchiseFeeRate franchiseFeeRate) {
        PaymentJpaEntity entity = new PaymentJpaEntity(
                requestMembershipId.getRequestMembershipId(),
                requestPrice.getRequestPrice(),
                franchiseId.getFranchiseId(),
                franchiseFeeRate.getFranchiseFeeRate(),
                0,
                LocalDateTime.now()
        );
        return this.paymentMapper.mapToDomainEntity(this.paymentRepository.save(entity));
    }
}
