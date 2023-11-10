package com.snowlightpay.membership.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlightpay.common.WebAdapter;
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
import org.springframework.web.bind.annotation.RestController;


@SpringBootTest
@AutoConfigureMockMvc
public class ModifyMembershipControllerTest {
    public static final String URL_UPDATE_MEMBERSHIP = "/membership/modify/{membershipId}";
    @Autowired MockMvc mockMvc;
    @Autowired private MembershipRepository membershipRepository;
    @Autowired private MembershipMapper membershipMapper;
    @Autowired private ObjectMapper objectMapper;

    @DisplayName("맴버 업데이트 테스트")
    @Test
    void test_modify_membership() throws Exception {
        MembershipJpaEntity entity = this.membershipRepository.save(new MembershipJpaEntity("name",
                "email",
                "address",
                true,
                true,
                ""));

        ModifyMembershipRequest modifyMembershipRequest = new ModifyMembershipRequest(entity.getMembershipId().toString(),
                                                                                "1-1",
                                                                                "1-1",
                                                                                "1-1",
                                                                                false,
                                                                                false,
                                                                                "");


        Membership membership = Membership.generateMember(
                new Membership.MembershipId(modifyMembershipRequest.getMembershipId()),
                new Membership.MembershipName("1-1"),
                new Membership.MembershipEmail("1-1"),
                new Membership.MembershipAddress("1-1"),
                new Membership.MembershipValid(false),
                new Membership.MembershipCorp(false),
                new Membership.MembershipRefreshToken("")
        );

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_UPDATE_MEMBERSHIP, entity.getMembershipId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifyMembershipRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(membership)));
    }
}
