package com.snowlightpay.membership.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.snowlightpay.membership.adapter.out.persistence.MembershipMapper;
import com.snowlightpay.membership.adapter.out.persistence.MembershipRepository;
import com.snowlightpay.membership.domain.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class FindMembershipControllerTest {
    public static final String URL_FIND_MEMBERSHIP = "/membership/{membershipId}";

    @Autowired private MockMvc mockMvc;
    @Autowired private MembershipRepository membershipRepository;
    @Autowired private MembershipMapper membershipMapper;
    @Autowired private ObjectMapper objectMapper;

    @DisplayName("맴버쉽 조회 테스트")
    @Test
    void test_find_membership() throws Exception {
        MembershipJpaEntity membershipJpaEntity = new MembershipJpaEntity("name",
                                                                            "email",
                                                                            "address",
                                                                            true,
                                                                            true);
        this.membershipRepository.save(membershipJpaEntity);

        Membership membership = membershipMapper.mapToDomainEntity(membershipJpaEntity);

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_FIND_MEMBERSHIP, "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("address"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valid").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.corp").value(true))
        ;
    }
}
