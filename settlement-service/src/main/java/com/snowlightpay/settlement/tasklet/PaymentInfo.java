package com.snowlightpay.settlement.tasklet;


import lombok.Data;

@Data
public class PaymentInfo {
    private String bankName;
    private String bankNumber;
    private int amount;

    public PaymentInfo(String bankName, String bankNumber) {
        this.bankName = bankName;
        this.bankNumber = bankNumber;
    }
}
