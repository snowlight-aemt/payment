package com.snowlightpay.remittance.application.service;

import com.snowlightpay.remittance.adapter.out.persistance.RemittanceMapper;
import com.snowlightpay.remittance.adapter.out.service.money.MemberMoney;
import com.snowlightpay.remittance.application.port.in.RemittanceCommand;
import com.snowlightpay.remittance.application.port.out.*;
import com.snowlightpay.remittance.domain.Remittance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
class RemittanceRequestServiceTest {
    @InjectMocks
    RemittanceRequestService remittanceRequestService;

    @Mock
    private RemittanceRequestPort remittanceRequestPort;
    @Mock
    private RemittanceMapper remittanceMapper;
    @Mock
    private MembershipPort membershipPort;
    @Mock
    private MoneyPort moneyPort;
    @Mock
    private RequestFirmBankingPort requestFirmBankingPort;

    @BeforeEach
    public void setup() {
//        MockitoAnnotations.openMocks(this);

        this.remittanceRequestService = new RemittanceRequestService(remittanceRequestPort, remittanceMapper, membershipPort, moneyPort, requestFirmBankingPort);
    }

//        1 먼저, 어떤 결과가 내올지에 대해서 정의
//        2 Mocking 위한 dummy data 가 있다면, 그 data 는 먼저 만들다.
//        3 그 결과를 위해, Mocking
//        4 그리고 그 Mocking 된 Mock 들을 새용해서, 테스트 진행
//        5 Verify 를 통해서, 테스트가 잘 진행되었는지 확인할거에요.
//        6 Assert 를 통해, 최종적으로 이 테스트가 잘 진행되었는지를 확인할거에요.

    public static Stream<RemittanceCommand> providerRequestRemittanceCommand() {
        return Stream.of(
                new RemittanceCommand(
                        "4",
                        "5",
                        "toBank",
                        "123-4566-1231",
                        0,
                        10000
                )
        );
    }

    @ParameterizedTest
    @DisplayName("고객 정보가 유효하지 않은 경우")
    @MethodSource("providerRequestRemittanceCommand")
    void remittanceRequestWhenInValidCustomer(RemittanceCommand command) {
        Remittance want = null;

        when(membershipPort.getMembership(command.getFromMembershipId()))
                .thenReturn(new MembershipStatus(command.getFromMembershipId(), false));

        Remittance got = remittanceRequestService.remittanceRequest(command);
        verify(membershipPort, times(1)).getMembership(command.getFromMembershipId());

        Assertions.assertThat(got).isEqualTo(want);
    }

    @ParameterizedTest
    @DisplayName("잔액이 충분하지 않은 경우 그리고 충전 머니가 없는 경우 ")
    @MethodSource("providerRequestRemittanceCommand")
    void remittanceRequestWhenNotEnoughMoney(RemittanceCommand command) {
        Remittance want = null;

        MemberMoney memberMoney = new MemberMoney(command.getFromMembershipId(), command.getFromMembershipId(), 1000);

        when(membershipPort.getMembership(command.getFromMembershipId()))
                .thenReturn(new MembershipStatus(command.getFromMembershipId(), true));
        when(this.moneyPort.getMemberMoney())
                .thenReturn(memberMoney);
        when(this.moneyPort.requestMoneyCharging())
                .thenReturn(new MoneyResult("fail"));

        Remittance got = remittanceRequestService.remittanceRequest(command);
        verify(membershipPort, times(1)).getMembership(command.getFromMembershipId());
        verify(moneyPort, times(1)).getMemberMoney();
        verify(moneyPort, times(1)).requestMoneyCharging();

        Assertions.assertThat(got).isEqualTo(want);
    }
}

/*
        MemberMoney memberMoney = this.moneyPort.getMemberMoney();
        if (memberMoney.getBalance() < command.getAmount()) {
            // 내 머니 충전
            MoneyResult moneyResult = this.moneyPort.requestMoneyCharging();
            if (moneyResult.equals("fail")) {
                return null;
            }
        }
 */