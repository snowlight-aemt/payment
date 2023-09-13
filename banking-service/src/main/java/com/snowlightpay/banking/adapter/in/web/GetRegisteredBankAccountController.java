package com.snowlightpay.banking.adapter.in.web;

import com.snowlightpay.banking.adapter.axon.aggregate.RegisteredBankAccountAggregate;
import com.snowlightpay.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.snowlightpay.banking.application.port.in.GetRegisteredBankAccountUseCase;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetRegisteredBankAccountController {
    private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

    @GetMapping("/banking/account/{membershipId}")
    public RegisterBankAccount getRegisteredBankAccount(@PathVariable String membershipId) {
        GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder()
                                                    .membershipId(membershipId).build();
        return this.getRegisteredBankAccountUseCase.getRegisteredBankAccount(command);
    }
}
