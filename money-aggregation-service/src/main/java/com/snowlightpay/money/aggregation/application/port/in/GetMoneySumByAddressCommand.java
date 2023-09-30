package com.snowlightpay.money.aggregation.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Data;

@Data
public class GetMoneySumByAddressCommand extends SelfValidating<GetMoneySumByAddressCommand> {
    private String address;

    public GetMoneySumByAddressCommand(String address) {
        this.address = address;

        this.validateSelf();
    }
}
