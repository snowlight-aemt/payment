package com.snowlightpay.remittance.adapter.out.persistance;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class RemittanceRequestJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long remittanceRequestId;

    private Long fromMembershipId;
    private Long toMembershipId;
    private String toBankAccountNumber;
    private String toBankName;
    private int remittanceType; // 0 membership, 1 backing
    private String remittanceStatus; // success fail
    private int amount;

    private String uuid;
}
