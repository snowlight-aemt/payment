package com.snowlightpay.money.aggregation.application.service;

import com.snowlightpay.common.UseCase;
import com.snowlightpay.money.aggregation.application.port.in.GetMoneySumByAddressCommand;
import com.snowlightpay.money.aggregation.application.port.in.GetMoneySumByAddressUseCase;
import com.snowlightpay.money.aggregation.application.port.out.FindMembershipByAddressPort;
import com.snowlightpay.money.aggregation.application.port.out.FindMoneyPort;
import com.snowlightpay.money.aggregation.application.port.out.MemberMoneyDto;
import com.snowlightpay.money.aggregation.application.port.out.MembershipDto;
import com.snowlightpay.money.aggregation.domain.MoneySum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class GetMoneySumByAddressService implements GetMoneySumByAddressUseCase {
    private final FindMembershipByAddressPort findMembershipByAddressPort;
    private final FindMoneyPort findMoneyPort;

    @Override
    public MoneySum getMoneySumByAddress(GetMoneySumByAddressCommand command) {
        List<MembershipDto> membershipByAddress = findMembershipByAddressPort.findMembershipByAddress(command.getAddress());
        List<List<String>> membershipPartitionList = null;
        if (membershipByAddress.size() > 100) {
            membershipPartitionList = partitionList(membershipByAddress, 100);
        } else {
            membershipPartitionList = new ArrayList<>();
            List<String> membershipIds = membershipByAddress.stream().
                                            map(MembershipDto::getMembershipId).collect(Collectors.toList());
            membershipPartitionList.add(membershipIds);
        }

        int totalAmount = 0;
        for (List<String> membershipIds : membershipPartitionList) {
            List<MemberMoneyDto> memberMoneyDtoList = findMoneyPort.findMoney(membershipIds);

            // VIEW
            memberMoneyDtoList.stream().forEach(m -> log.info(String.valueOf(m.getBalance())));
            totalAmount += memberMoneyDtoList.stream().mapToInt(MemberMoneyDto::getBalance).sum();
        }

        return MoneySum.generateMoneySum(new MoneySum.Address(command.getAddress()),
                                                        new MoneySum.TotalAmount(totalAmount));
    }

    private List<List<String>> partitionList(List<MembershipDto> list, int partitionSize) {
        return IntStream.range(0, list.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / partitionSize))
                .values()
                .stream()
                .map(indices -> indices.stream()
                                    .map(list::get)
                                    .map(MembershipDto::getMembershipId)
                                    .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
