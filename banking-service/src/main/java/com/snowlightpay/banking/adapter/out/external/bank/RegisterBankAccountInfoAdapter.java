package com.snowlightpay.banking.adapter.out.external.bank;

import com.snowlightpay.banking.application.port.out.RegisterBankAccountInfoPort;
import com.snowlightpay.banking.application.port.out.RequestExternalFirmBankPort;
import com.snowlightpay.banking.domain.RegisterBankAccount;
import com.snowlightpay.banking.domain.RequestFirmBank;
import com.snowlightpay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class RegisterBankAccountInfoAdapter implements RegisterBankAccountInfoPort, RequestExternalFirmBankPort {
    @Override
    public BankAccount findRegisterBankAccountInfo(RegisterBankAccount.BankName bankName,
                                                   RegisterBankAccount.BankAccountNumber bankAccountNumber) {
        return new BankAccount(bankName.getBackName(), bankAccountNumber.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestFirmBanking(RequestFirmBank.FromBankName fromBankName,
                                   RequestFirmBank.FromBankAccountNumber fromBankAccountNumber,
                                   RequestFirmBank.ToBankName toBankName,
                                   RequestFirmBank.ToBankAccountNumber toBankAccountNumber) {
        return new FirmBankingResult(0);
    }
}
