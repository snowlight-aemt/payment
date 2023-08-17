package com.snowlightpay.banking.application.service;

import com.snowlightpay.banking.application.port.in.RequestFirmBankingCommand;
import com.snowlightpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.UseCase;

@UseCase
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    @Override
    public RequestFirmBank requestFirmBanking(RequestFirmBankingCommand command) {

        // 1. 요청에 대해 정보를 먼저 체크 (요청)

        // 2. 외부 은행에 펌뱅킹 요청

        // 3. 결과에 따라서 1번 Update

        // 4. 결과 리턴

        return null;
    }
}
