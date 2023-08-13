package com.snowlightpay.banking.adapter.out.external.bank;

import com.snowlightpay.banking.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class RegisterBankAccountInfoAdapter implements RegisterBankAccountInfoPort {
    @Override
    public BankAccount findRegisterBankAccountInfo(RegisterBankAccount.BankName bankName,
                                                   RegisterBankAccount.BankAccountNumber bankAccountNumber) {
        return new BankAccount(bankName.getBackName(), bankAccountNumber.getBankAccountNumber(), true);
    }
}
