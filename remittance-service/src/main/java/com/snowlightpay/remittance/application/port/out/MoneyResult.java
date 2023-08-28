package com.snowlightpay.remittance.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyResult {
    private String MoneyResultType; // success fail
}
