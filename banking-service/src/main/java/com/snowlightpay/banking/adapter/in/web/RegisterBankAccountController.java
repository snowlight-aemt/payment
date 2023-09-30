package com.snowlightpay.banking.adapter.in.web;

import com.snowlightpay.banking.application.port.in.RegisterBankAccountUseCase;
import com.snowlightpay.banking.application.port.in.RegisterBankAccountCommand;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {
    private final RegisterBankAccountUseCase registerBankAccountUseCase;
    @PostMapping("/banking/account/register")
    public ResponseEntity<RegisterBankAccount> registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.getMembershipId(),
                                                                            request.getBankName(),
                                                                            request.getBankAccountNumber(),
                                                                            request.isLinkedStatusIsValid(),
                                                                            "");

        RegisterBankAccount registerBankAccount = registerBankAccountUseCase.createBankAccount(command);
        return ResponseEntity.ok(registerBankAccount);
    }


    @PostMapping("/banking/account/register-eda")
    public void registerBankAccountByEvent(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.getMembershipId(),
                request.getBankName(),
                request.getBankAccountNumber(),
                request.isLinkedStatusIsValid(),
                "");

        registerBankAccountUseCase.createBankAccountByEvent(command);
    }
}
