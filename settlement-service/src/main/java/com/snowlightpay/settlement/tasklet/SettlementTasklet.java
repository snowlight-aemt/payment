package com.snowlightpay.settlement.tasklet;

import com.snowlightpay.settlement.adapter.out.service.Payment;
import com.snowlightpay.settlement.adapter.out.service.PaymentIdRequest;
import com.snowlightpay.settlement.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.settlement.port.out.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SettlementTasklet implements  Tasklet{
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    private final PaymentPort paymentPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        System.out.println("Tasklet Execute!: "
                + chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobParameters()
                    .getLong("time"));

        List<Payment> payments = paymentPort.payment();

        Map<String, PaymentInfo> map = new HashMap<>();
        for (Payment p : payments) {
            var registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount(p.getRequestMembershipId());
            map.put(p.getFranchiseId(),
                    new PaymentInfo(registeredBankAccount.getBankName(), registeredBankAccount.getBankAccountNumber()));
        }

        for (Payment p : payments) {
            // 수료료
            PaymentInfo paymentInfo = map.get(p.getRequestMembershipId());
            paymentInfo.setAmount(paymentInfo.getAmount() + Integer.parseInt(p.getRequestPrice()));
        }

        for (PaymentInfo pi : map.values()) {
            getRegisteredBankAccountPort.requestFirmBanking(pi.getBankName(), pi.getBankNumber(), pi.getAmount());
        }

        for (Payment p : payments) {
            paymentPort.updatePaymentStatus(new PaymentIdRequest(p.getPaymentId()));
        }

        return RepeatStatus.FINISHED;
    }
}
