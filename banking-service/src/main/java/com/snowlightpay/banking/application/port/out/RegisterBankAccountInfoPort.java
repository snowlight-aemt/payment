package com.snowlightpay.banking.application.port.out;

import com.snowlightpay.banking.adapter.out.external.bank.BankAccount;
import com.snowlightpay.banking.domain.RegisterBankAccount;

public interface RegisterBankAccountInfoPort {
    BankAccount findRegisterBankAccountInfo(RegisterBankAccount.BankName bankName,
                                            RegisterBankAccount.BankAccountNumber bankAccountNumber);
}