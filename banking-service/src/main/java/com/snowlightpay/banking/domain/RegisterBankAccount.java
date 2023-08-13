package com.snowlightpay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisterBankAccount {
    private String bankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    public static RegisterBankAccount generateMember (BankAccountId bankAccountId, MembershipId membershipId,
                                                      BankName bankName,
                                                      BankAccountNumber bankAccountNumber,
                                                      LinkedStatusIsValid linkedStatusIsValid) {
        return new RegisterBankAccount(
                bankAccountId.getBankAccountId(),
                membershipId.getMembershipId(),
                bankName.getBackName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid());
    }


    @Value
    public static class BankAccountId {
        public BankAccountId(String bankAccountId) { this.bankAccountId = bankAccountId; }
        String bankAccountId;
    }
    @Value
    public static class MembershipId {
        public MembershipId(String membershipId) { this.membershipId = membershipId; }
        String membershipId;
    }
    @Value
    public static class BankName {
        public BankName(String bankName) { this.backName = bankName; }
        String backName;
    }
    @Value
    public static class BankAccountNumber {
        public BankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }
        String bankAccountNumber;
    }
    @Value
    public static class LinkedStatusIsValid {
        public LinkedStatusIsValid(boolean linkedStatusIsValid) { this.linkedStatusIsValid = linkedStatusIsValid; }
        boolean linkedStatusIsValid;
    }
}
