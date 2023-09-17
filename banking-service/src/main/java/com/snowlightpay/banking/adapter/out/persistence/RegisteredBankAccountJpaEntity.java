package com.snowlightpay.banking.adapter.out.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "registered_banking_account")
public class RegisteredBankAccountJpaEntity {
    @GeneratedValue
    @Id
    private Long backAccountId;
    private Long membershipId;
    private String bankName;
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
    private String aggregateIdentifier;

    public RegisteredBankAccountJpaEntity(Long membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid, String aggregateIdentifier) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.membershipId = membershipId;
        this.linkedStatusIsValid = linkedStatusIsValid;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    @Override
    public String toString() {
        return "RegisteredBankAccountJpaEntity{" +
                "backAccountId=" + backAccountId +
                ", membershipId=" + membershipId +
                ", bankName='" + bankName + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", linkedStatusIsValid=" + linkedStatusIsValid +
                ", aggregateIdentifier='" + aggregateIdentifier + '\'' +
                '}';
    }
}
