package com.snowlightpay.money.adapter.axon.saga;

import com.snowlightpay.common.event.CheckRegisteredBankAccountCommand;
import com.snowlightpay.common.event.CheckRegisteredBankAccountEvent;
import com.snowlightpay.common.event.RequestFirmbankingCommand;
import com.snowlightpay.common.event.RequestFirmbankingEvent;
import com.snowlightpay.money.adapter.axon.event.RechargingRequestCreatedEvent;
import com.snowlightpay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.snowlightpay.money.application.port.out.IncreaseMoneyRequestPort;
import com.snowlightpay.money.domain.MemberMoney;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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

        String checkRegisteredBankAccountId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("checkRegisteredBankAccountId", checkRegisteredBankAccountId);
//
        // 충전 요청 - 시작

        // 뱅킹의 계좌 등록 여부 확인 (RegisteredBankAccount)
        // CheckRegisteredBankAccountCommand
        // -> axon server -> Banking Service (공통 모듈)
        this.commandGateway.send(new CheckRegisteredBankAccountCommand(event.getRegisteredBankAccountAggregateIdentifier(),
                                                                        event.getRechargingRequestId(),
                                                                        event.getMembershipId(),
                                                                        checkRegisteredBankAccountId,
                                                                        event.getBankName(),
                                                                        event.getBackAccountNumber(),
                                                                        event.getAmount())
        ).whenComplete((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                System.out.println("CheckRegisteredBankAccountCommand command failed");
            } else {
                System.out.println("CheckRegisteredBankAccountCommand command success");
            }
        });
    }

    @SagaEventHandler(associationProperty = "checkRegisteredBankAccountId")
    public void handle(CheckRegisteredBankAccountEvent event) {
        System.out.println("CheckedRegisteredAccountEvent saga : "  + event.toString());

        boolean status = event.isChecked();

        if (status) {
            System.out.println("CheckedRegisteredBankAccountEvent event success");
        } else {
            System.out.println("CheckedRegisteredBankAccountEvent event failed");
        }

        // 송금 요청
        // 고객 -> 법인
        String requestFirmbankingId = UUID.randomUUID().toString();
        SagaLifecycle.associateWith("requestFirmbankingId", requestFirmbankingId);

        commandGateway.send(new RequestFirmbankingCommand(
            requestFirmbankingId,
            event.getFirmbankingRequestAggregateIdentifier(),
            event.getRechargingRequestId(),
            event.getMembershipId(),
            event.getFromBankName(),
            event.getFromBankAccountNumber(),
            "snowcampus",
            "123456",
            event.getAmount()
        )).whenComplete((result, throwable) -> {
            if (throwable != null) {

            } else {

            }
        });
    }

    @SagaEventHandler(associationProperty = "requestFirmbankingId")
    public void handle(RequestFirmbankingEvent event, IncreaseMoneyRequestPort increaseMoneyRequestPort) {
        System.out.println("RequestFirmbankingId event : " + event.toString());

        boolean status = event.getStatus() == 0;

        if (status) {
            System.out.println("RequestFirmbankingFininshedEvent event success");
        } else {
            System.out.println("RequestFirmbankingFininshedEvent event failed");
        }

        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyRequestPort.increaseMemberMoney(
                new MemberMoney.MembershipId(event.getMembershipId()),
                new MemberMoney.Balance(event.getMoneyAmount())
        );
    }


}
