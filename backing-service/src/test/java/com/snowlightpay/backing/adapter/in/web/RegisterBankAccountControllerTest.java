package com.snowlightpay.backing.adapter.in.web;

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

import static org.junit.jupiter.api.Assertions.*;

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
}