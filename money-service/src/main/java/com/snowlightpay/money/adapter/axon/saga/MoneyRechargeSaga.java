package com.snowlightpay.money.adapter.axon.saga;

import com.snowlightpay.common.event.CheckRegisteredBankAccountCommand;
import com.snowlightpay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@NoArgsConstructor
public class MoneyRechargeSaga {
    @NonNull
    private transient CommandGateway commandGateway;
    @Autowired
    private void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "rechargingRequestId")
    public void handle(RechargingRequestCreatedEvent event) {
        System.out.println("RechargingRequestCreatedEvent Start saga");

        String rechargingRequestId = event.getRechargingRequestId();

//        SagaLifecycle.associateWith("rechargingRequestId", rechargingRequestId);
//
        // 충전 요청 - 시작

        // 뱅킹의 계좌 등록 여부 확인 (RegisteredBankAccount)
        // CheckRegisteredBankAccountCommand
        // -> axon server -> Banking Service (공통 모듈)
//        this.commandGateway.send(new CheckRegisteredBankAccountCommand("",
//                                                                        rechargingRequestId,
//                                                                        event.getMembershipId()));


    }
}
