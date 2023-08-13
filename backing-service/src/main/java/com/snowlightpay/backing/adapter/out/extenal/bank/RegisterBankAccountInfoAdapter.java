package com.snowlightpay.backing.adapter.out.extenal.bank;

import com.snowlightpay.backing.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.backing.domain.RegisterBankAccount;
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
