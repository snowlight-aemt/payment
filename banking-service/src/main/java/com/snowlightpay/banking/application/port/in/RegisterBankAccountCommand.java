package com.snowlightpay.banking.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;
import lombok.Setter;

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
