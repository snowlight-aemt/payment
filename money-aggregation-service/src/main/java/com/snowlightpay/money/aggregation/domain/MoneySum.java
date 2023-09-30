package com.snowlightpay.money.aggregation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class MoneySum {
    private String address;
    private int totalAmount;

    public static MoneySum generateMoneySum(Address address,
                                               TotalAmount totalAmount) {
        return new MoneySum(address.getAddress(), totalAmount.getTotalAmount());
    }

    @Value
    public static class Address {
        public Address(String address) { this.address = address; }
        String address;
    }

    @Value
    public static class TotalAmount {
        public TotalAmount(int totalAmount) { this.totalAmount = totalAmount; }
        int totalAmount;
    }
}
