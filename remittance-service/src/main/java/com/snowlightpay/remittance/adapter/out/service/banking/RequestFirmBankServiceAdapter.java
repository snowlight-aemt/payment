package com.snowlightpay.remittance.adapter.out.service.banking;

import com.snowlightpay.remittance.application.port.out.RequestFirmBankingPort;
import org.springframework.stereotype.Component;

@Component
public class RequestFirmBankServiceAdapter implements RequestFirmBankingPort {
    @Override
    public boolean requestFirmBanking() {
        return true;
    }
}
