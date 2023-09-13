package com.snowlightpay.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor
public class RegisterBankAccount {
    private String registeredBankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
    private String aggregateIdentifier;


    public static RegisterBankAccount generateMember (BankAccountId bankAccountId, MembershipId membershipId,
                                                      BankName bankName,
                                                      BankAccountNumber bankAccountNumber,
                                                      LinkedStatusIsValid linkedStatusIsValid,
                                                      AggregateIdentifier aggregateIdentifier) {
        return new RegisterBankAccount(
                bankAccountId.getBankAccountId(),
                membershipId.getMembershipId(),
                bankName.getBackName(),
                bankAccountNumber.getBankAccountNumber(),
                linkedStatusIsValid.isLinkedStatusIsValid(),
                aggregateIdentifier.getAggregateIdentifier());
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
    @Value
    public static class AggregateIdentifier {
        public AggregateIdentifier(String aggregateIdentifier) { this.aggregateIdentifier = aggregateIdentifier; }
        String aggregateIdentifier;
    }
}
