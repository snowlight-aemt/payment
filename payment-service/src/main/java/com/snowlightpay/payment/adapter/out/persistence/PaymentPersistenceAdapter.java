package com.snowlightpay.payment.adapter.out.persistence;

import com.snowlightpay.payment.application.port.out.CreatePaymentPort;
import com.snowlightpay.payment.application.port.out.GetPaymentPort;
import com.snowlightpay.payment.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaymentPersistenceAdapter implements CreatePaymentPort, GetPaymentPort {
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

    @Override
    public List<Payment> getPayment() {
        return this.paymentRepository.findAllByPaymentStatus(0).stream()
                .map(paymentMapper::mapToDomainEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Payment updatePaymentBy(Long paymentId, int status) {
        PaymentJpaEntity entity = this.paymentRepository.findById(paymentId)
                                        .orElseThrow(IllegalArgumentException::new);
        entity.setPaymentStatus(status);
        PaymentJpaEntity save = this.paymentRepository.save(entity);

        return this.paymentMapper.mapToDomainEntity(save);
    }
}
