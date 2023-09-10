package com.snowlightpay.banking.adapter.in.web;

import com.snowlightpay.banking.application.port.in.RequestFirmBankingCommand;
import com.snowlightpay.banking.application.port.in.RequestFirmBankingUseCase;
import com.snowlightpay.banking.application.port.in.UpdateFirmBankingCommand;
import com.snowlightpay.banking.application.port.in.UpdateFirmBankingUseCase;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {
    private final RequestFirmBankingUseCase requestFirmBankingUseCase;
    private final UpdateFirmBankingUseCase updateFirmBankingUseCase;
    @PostMapping("/banking/firmBanking/request")
    public ResponseEntity<RequestFirmBank> requireFirmBanking(@RequestBody RequestFirmBanking request) {
        RequestFirmBankingCommand command = new RequestFirmBankingCommand(
                    request.getFromBankAccountNumber(), request.getFromBankName(),
                    request.getToBankAccountNumber(), request.getToBankName(),
                    request.getMoneyAmount());

        RequestFirmBank requestFirmBank = requestFirmBankingUseCase.requestFirmBanking(command);
        return ResponseEntity.ok(requestFirmBank);
    }

    @PostMapping("/banking/firmBanking/request-eda")
    public void requireFirmBankingByEvent(@RequestBody RequestFirmBanking request) {
        RequestFirmBankingCommand command = new RequestFirmBankingCommand(
                request.getFromBankAccountNumber(), request.getFromBankName(),
                request.getToBankAccountNumber(), request.getToBankName(),
                request.getMoneyAmount());

        requestFirmBankingUseCase.requestFirmBankingByEvent(command);
    }

    @PutMapping("/banking/firmBanking/request-eda")
    public void updateFirmBankingByEvent(@RequestBody UpdateFirmBanking request) {
        UpdateFirmBankingCommand command = new UpdateFirmBankingCommand(request.getFirmBankingId(), request.getStatus());

        updateFirmBankingUseCase.updateFirmBanking(command);
    }
}
