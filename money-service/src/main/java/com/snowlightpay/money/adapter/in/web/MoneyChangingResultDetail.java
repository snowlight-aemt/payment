package com.snowlightpay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;
    private int moneyChangingType; // 0: 증액, 1: 감액
    private int moneyChangingResultStatus; // 0: 성공, 1: 실패, 실패 - 잔액 부족, 실패 - 맴버실 없음, 실패 - 머니 변액 요청 없음
    private int amount;
}