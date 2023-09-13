package com.snowlightpay.banking.adapter.axon.command;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegisteredBankAccountCommand {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
}
