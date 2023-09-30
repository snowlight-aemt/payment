package com.snowlightpay.money.aggregation.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.money.aggregation.application.port.out.FindMembershipByAddressPort;
import com.snowlightpay.money.aggregation.application.port.out.MembershipDto;
import com.snowlightpay.money.aggregation.domain.MoneySum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindMembershipByAddressServiceAdapter implements FindMembershipByAddressPort {
    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;

    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public List<MembershipDto> findMembershipByAddress(String address) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(
                                            String.join("/", membershipServiceUrl, "membership/address"));
        String url = builder.queryParam("address", address).toUriString();
        try {
            String jsonResponse = commonHttpClient.sendGetRequest(url).body();
            List<Membership> memberships = this.objectMapper.readValue(jsonResponse, new TypeReference<List<Membership>>() {});

            // membership 유효성 체크 생략

            return memberships.stream().map(membership -> new MembershipDto(membership.getMembershipId(),
                                                                        membership.getAddress(),
                                                                        membership.isValid(),
                                                                        membership.isCorp())).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
