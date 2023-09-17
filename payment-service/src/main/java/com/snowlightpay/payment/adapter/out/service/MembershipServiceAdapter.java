package com.snowlightpay.payment.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.CommonHttpClient;
import com.snowlightpay.payment.application.port.out.GetMembershipPort;
import com.snowlightpay.payment.application.port.out.MembershipStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MembershipServiceAdapter implements GetMembershipPort {
    private final CommonHttpClient commonHttpClient;
    private final ObjectMapper objectMapper;
    @Value("${service.membership.url}")
    private String membershipServiceUrl;

    @Override
    public MembershipStatus getMembership(String membershipId) {
        log.info("getMembership ");
        String url = String.join("/", membershipServiceUrl, "membership", membershipId);
        try {
            String jsonResponse = this.commonHttpClient.sendGetRequest(url).body();
            log.info("getMembership {}", jsonResponse);
            Membership membership = this.objectMapper.readValue(jsonResponse, Membership.class);

            if (!membership.isValid()) {
                return new MembershipStatus(membership.getMembershipId(),true);
            } else {
                return new MembershipStatus(membership.getMembershipId(),false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
