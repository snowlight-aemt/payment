package com.snowlightpay.backing.adapter.in.web;

import com.snowlightpay.backing.application.port.in.RegisterBankAccountUseCase;
import com.snowlightpay.backing.application.port.in.RegisterBankAccountCommand;
import com.snowlightpay.backing.domain.RegisterBankAccount;
import com.snowlightpay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {
    private final RegisterBankAccountUseCase registerBankAccountUseCase;
    @PostMapping("/backing/account/register")
    public ResponseEntity<RegisterBankAccount> hello(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = new RegisterBankAccountCommand(request.getMembershipId(),
                                                                            request.getBankName(),
                                                                            request.getBankAccountNumber(),
                                                                            request.isLinkedStatusIsValid());

        RegisterBankAccount registerBankAccount = registerBankAccountUseCase.createBankAccount(command);
        return ResponseEntity.ok(registerBankAccount);
    }
}
