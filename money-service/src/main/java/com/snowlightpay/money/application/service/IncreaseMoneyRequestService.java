package com.snowlightpay.money.application.service;

import com.snowlightpay.common.CountDownLatchManager;
import com.snowlightpay.common.RechargingMoneyTask;
import com.snowlightpay.common.SubTask;
import com.snowlightpay.common.UseCase;
import com.snowlightpay.money.adapter.axon.command.MemberMoneyCreatedCommand;
import com.snowlightpay.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.snowlightpay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyCommand;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyUseCase;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.snowlightpay.money.application.port.out.*;
import com.snowlightpay.money.domain.MemberMoney;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase, CreateMemberMoneyUseCase {
    private final GetMembershipPort getMembershipPort;
    private final IncreaseMoneyRequestPort increaseMoneyRequestPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;

    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final CountDownLatchManager countDownLatchManager;

    private final CreateMemberMoneyPort createMemberMoneyPort;

    private final CommandGateway commandGateway;

    @Override
    public MoneyChangingRequest increaseMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        // ToDo MoneyChanging 비즈니스 로직 구현

        // 1. 고객 정보 확인. (맴버)
        MembershipStatus membership = getMembershipPort.getMembership(command.getTargetMembershipId());
        // 2. 고객 계좌 확인. (뱅킹)
        // 3. 법인 계좌 확인. (뱅킹)

        // 4. 증액을 위한 *기록* 을 한다. (MoneyChangingRequest 저장)
        MoneyChangingRequestJpaEntity moneyChanging = this.increaseMoneyRequestPort.createMoneyChanging(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                new MoneyChangingRequest.MoneyChangingAmount(command.getMoneyChangingAmount()),
                new MoneyChangingRequest.ChangingType(0),
                new MoneyChangingRequest.MoneyChangingStatus(0)
        );

        // 5. 고객 계좌에서 법인 계좌로 출금. (뱅킹)

        // 6-1. 성공하면 MoneyChangingRequest 업데이트) 리턴
        // 6-1-1 맴버의 실제 머니 충전
        moneyChanging.moneyChangingSuccess();
        // ToDo 더티 체크로 업데이트 되는데?? 필요한가??
//        moneyChanging = this.increaseMoneyRequestPort.modifyMoneyChanging(moneyChanging);

        if (moneyChanging.getMoneyChangingStatus() == 1) {
            this.increaseMoneyRequestPort.increaseMemberMoney(
                    new MemberMoney.MembershipId(moneyChanging.getTargetMembershipId()+""),
                    new MemberMoney.Balance(moneyChanging.getMoneyChangingAmount()));
        }

        return this.moneyChangingRequestMapper.mapToDomainEntity(moneyChanging);
        // 6-2. 실패하면 MoneyChangingRequest 업데이트) 리턴

    }

    @Override
    public MoneyChangingRequest increaseMoneyChangingRequestAsync(IncreaseMoneyRequestCommand command) {
        // 1. Task SubTask
        // 1-1 맴버쉽 유효성 체크
        SubTask membershipSubTask = SubTask.builder()
                .membershipId(command.getTargetMembershipId())
                .subTypeName("validMemberTask: " + "맴버 유효성 검사")
                .taskType("membership")
                .status("ready")
                .build();

        // 1-2 뱅킹 유효성 체크
        SubTask bankingSubTask = SubTask.builder()
                .membershipId(command.getTargetMembershipId())
                .subTypeName("validBankingAccountTask: " + "뱅킹 계정 유효성 검사")
                .taskType("banking")
                .status("ready")
                .build();

        // ToDo 1-3 펌뱅킹 유효성 체크 (제외)

        List<SubTask> subTaskList = List.of(membershipSubTask, bankingSubTask) ;
        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .membershipId(command.getTargetMembershipId())
                .taskName("Increase Money Task: " + "머니 충전 Task")
                .subTaskList(subTaskList)
                .toBankName("스노우 뱅크")
                .toBankAccountNumber("101-222-232232")
                .moneyAmount(command.getMoneyChangingAmount())
                .build();

        // 2. Producer
        sendRechargingMoneyTaskPort.SendRechargingMoneyTaskPort(task);

        // 3. Wait - Task 가 전부 끝날때 까지 대기
        // 3-1. 다른 곳에서 Consumer 한다.
        this.countDownLatchManager.addCountDownLatch(task.getTaskID());
//
        try {
            this.countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 4. Result Producer
        String result = this.countDownLatchManager.getDataForKey(task.getTaskID());
        if (result.equals("success")) {
            // _4. 증액을 위한 *기록* 을 한다. (MoneyChangingRequest 저장)
            MoneyChangingRequestJpaEntity moneyChanging = this.increaseMoneyRequestPort.createMoneyChanging(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.MoneyChangingAmount(command.getMoneyChangingAmount()),
                    new MoneyChangingRequest.ChangingType(0),
                    new MoneyChangingRequest.MoneyChangingStatus(0)
            );

            // _5. 고객 계좌에서 법인 계좌로 출금. (뱅킹)

            // _6-1. 성공하면 MoneyChangingRequest 업데이트) 리턴
            // _6-1-1 맴버의 실제 머니 충전
            moneyChanging.moneyChangingSuccess();
            // ToDo 더티 체크로 업데이트 되는데?? 필요한가??
            // moneyChanging = this.increaseMoneyRequestPort.modifyMoneyChanging(moneyChanging);

            if (moneyChanging.getMoneyChangingStatus() == 1) {
                this.increaseMoneyRequestPort.increaseMemberMoney(
                        new MemberMoney.MembershipId(moneyChanging.getTargetMembershipId()+""),
                        new MemberMoney.Balance(moneyChanging.getMoneyChangingAmount()));
            }

            return this.moneyChangingRequestMapper.mapToDomainEntity(moneyChanging);
        } else {
            // _6-2. 실패하면 MoneyChangingRequest 업데이트) 리턴
            return null;
        }

        // 5. Result Consumer
    }

    @Override
    public void createMemberMoney(CreateMemberMoneyCommand command) {
        MemberMoneyCreatedCommand axonCommand = new MemberMoneyCreatedCommand(command.getMemberShipId());

        // Send - Event Queue
        CompletableFuture<Object> send = this.commandGateway.send(axonCommand);
        send.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("throwable = " + throwable);
                throw new RuntimeException(throwable);
            } else {
                log.info("result = " + result);
                createMemberMoneyPort.createMemberMoney(
                        new MemberMoney.MembershipId(command.getMemberShipId()),
                        new MemberMoney.MoneyAggregateIdentifier(result.toString())
                );
            }
        });
    }
}
