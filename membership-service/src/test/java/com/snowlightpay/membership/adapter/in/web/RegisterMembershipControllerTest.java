package com.snowlightpay.membership.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.membership.domain.Membership;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterMembershipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("맴버 생성 테스트")
    @Test
    void test_create_membership() throws Exception {
        RegisterMembershipRequest registerMembershipRequest = new RegisterMembershipRequest(
                "name", "email", "address", true
        );

        Membership membership = Membership.generateMember(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipValid(true),
                new Membership.MembershipCorp(true)
        );

        this.mockMvc.perform(MockMvcRequestBuilders.post("/membership/register")
                        .content(objectMapper.writeValueAsString(registerMembershipRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(membership)))
                .andDo(MockMvcResultHandlers.print());
    }
}