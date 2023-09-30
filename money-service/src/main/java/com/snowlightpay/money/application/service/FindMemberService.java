package com.snowlightpay.money.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.money.application.port.in.FindMoneyUseCase;
import com.snowlightpay.money.application.port.out.GetMemberMoneyPort;
import com.snowlightpay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class FindMemberService implements FindMoneyUseCase {
    private final GetMemberMoneyPort getMemberMoneyPort;

    @Override
    public List<MemberMoney> findMemberMoneyListByMembershipIds(List<String> membershipIds) {
        List<MemberMoney.MembershipId> convertedMembershipIds = membershipIds.stream()
                                                                        .map(MemberMoney.MembershipId::new)
                                                                        .collect(Collectors.toList());

        return getMemberMoneyPort.findMemberMoneyListByMembershipIds(convertedMembershipIds);
    }
}
