package com.snowlightpay.backing.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {
    private String bankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    public RegisterBankAccountCommand(String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;

        this.validateSelf();
    }
}
