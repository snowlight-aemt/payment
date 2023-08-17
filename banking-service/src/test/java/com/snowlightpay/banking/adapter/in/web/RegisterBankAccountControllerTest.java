package com.snowlightpay.banking.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class RegisterBankAccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("뱅킹 계정 등록 테스트")
    @Test
    void test_register_bank_account() throws Exception {
        RegisterBankAccountRequest request = new RegisterBankAccountRequest("1",
                "신한",
                "123-101-123456",
                true);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/backing/account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bankName").value("신한"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bankAccountNumber").value("123-101-123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.linkedStatusIsValid").value(true))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("펌뱅킹 송금 테스트")
    @Test
    void test_firm_bank_() throws Exception {
        RequestFirmBanking request = new RequestFirmBanking("101-111-1111",
                                                                "신한은행",
                                                                "202-222-2222",
                                                                "우리은행",
                                                                10000);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/banking/firmBanking/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firmBankingId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fromBankAccountNumber").value("101-111-1111"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fromBankName").value("신한은행"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toBankAccountNumber").value("202-222-2222"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.toBankName").value("우리은행"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.moneyAmount").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firmBankingStatus").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").exists())
                .andDo(MockMvcResultHandlers.print());
    }
}