package com.snowlightpay.payment.application.service;

import com.snowlightpay.payment.application.port.in.PaymentIdCommand;
import com.snowlightpay.payment.application.port.in.RequestPaymentCommand;
import com.snowlightpay.payment.application.port.in.RequestPaymentUseCase;
import com.snowlightpay.payment.application.port.out.*;
import com.snowlightpay.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestPaymentService implements RequestPaymentUseCase {
    private final CreatePaymentPort createPaymentPort;
    private final GetPaymentPort getPaymentPort;
    private final GetMembershipPort getMembershipPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    @Override
    public Payment payment(RequestPaymentCommand command) {

        // 유효성 체크
        MembershipStatus membership = getMembershipPort.getMembership(command.getRequestMembershipId());
        if (!membership.isValid()) {
            System.out.println("유효하지 않는 맴버쉽 입니다.");
            return null;
        }

        // 유효성 체크
        getRegisteredBankAccountPort.getRegisteredBankAccount(command.getRequestMembershipId());

        // --------------

        return this.createPaymentPort.createPayment(
                new Payment.RequestMembershipId(command.getRequestMembershipId()),
                new Payment.RequestPrice(command.getRequestPrice()),
                new Payment.FranchiseId(command.getFranchiseId()),
                new Payment.FranchiseFeeRate(command.getFranchiseFeeRate())
        );
    }

    @Override
    public List<Payment> getPaymentsByApprove() {
        return this.getPaymentPort.getPayment();
    }

    @Override
    public Payment finishSettlement(PaymentIdCommand paymentIdCommand) {
        return this.getPaymentPort.updatePaymentBy(paymentIdCommand.getPaymentId(), 2);
    }
}
