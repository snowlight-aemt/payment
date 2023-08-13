package com.snowlightpay.backing.application.port.out;

import com.snowlightpay.backing.adapter.out.extenal.bank.BankAccount;
import com.snowlightpay.backing.domain.RegisterBankAccount;

public interface RegisterBankAccountInfoPort {
    BankAccount findRegisterBankAccountInfo(RegisterBankAccount.BankName bankName,
                                            RegisterBankAccount.BankAccountNumber bankAccountNumber);
}