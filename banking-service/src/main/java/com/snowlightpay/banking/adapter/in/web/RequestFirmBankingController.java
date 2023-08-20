package com.snowlightpay.banking.adapter.in.web;

import com.snowlightpay.banking.application.port.in.RequestFirmBankingCommand;
import com.snowlightpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {
    private final RequestFirmBankingUseCase requestFirmBankingUseCase;
    @PostMapping("/banking/firmBanking/request")
    public ResponseEntity<RequestFirmBank> requireFirmBanking(@RequestBody RequestFirmBanking request) {
        RequestFirmBankingCommand command = new RequestFirmBankingCommand(
                    request.getFromBankAccountNumber(), request.getFromBankName(),
                    request.getToBankAccountNumber(), request.getToBankName(),
                    request.getMoneyAmount());

        RequestFirmBank requestFirmBank = requestFirmBankingUseCase.requestFirmBanking(command);
        return ResponseEntity.ok(requestFirmBank);
    }
}
