package com.snowlightpay.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmBanking {
    private String fromBankAccountNumber;
    private String fromBankName;
    private String toBankAccountNumber;
    private String toBankName;
    private int moneyAmount;
}
