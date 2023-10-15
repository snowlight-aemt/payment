package com.snowlightpay.settlement.tasklet;

import com.snowlightpay.settlement.port.out.GetRegisteredBankAccountPort;
import com.snowlightpay.settlement.port.out.RegisteredBankAccountAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettlementTasklet implements  Tasklet{
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext){
        System.out.println("Tasklet Execute!: "
                + chunkContext.getStepContext()
                .getStepExecution()
                .getJobParameters()
                .getLong("time"));


        var registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount("1");
        System.out.println(registeredBankAccount);

        return RepeatStatus.FINISHED;
    }
}
