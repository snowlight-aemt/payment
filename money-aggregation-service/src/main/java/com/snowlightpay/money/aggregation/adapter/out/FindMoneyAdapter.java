package com.snowlightpay.money.aggregation.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.money.aggregation.application.port.out.FindMoneyPort;
import com.snowlightpay.money.aggregation.application.port.out.MemberMoneyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindMoneyAdapter implements FindMoneyPort {
    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${service.money.url}")
    private String moneyServiceUrl;

    @Override
    public List<MemberMoneyDto> findMoney(List<String> membershipIds) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(
                String.join("/", moneyServiceUrl,"money/member-money"));
        String url = builder.queryParam("membershipIds", membershipIds).toUriString();
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            List<MemberMoney> memberMoneys = this.objectMapper.readValue(jsonResponse, new TypeReference<List<MemberMoney>>() {});

            // membership 유효성 체크 생략

            return memberMoneys.stream().map(memberMoney -> new MemberMoneyDto(memberMoney.getMemberMoneyId(),
                                                                                memberMoney.getMembershipId(),
                                                                                memberMoney.getBalance()))
                                                                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
