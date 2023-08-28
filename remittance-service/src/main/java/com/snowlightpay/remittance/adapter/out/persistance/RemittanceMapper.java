package com.snowlightpay.remittance.adapter.out.persistance;

import com.snowlightpay.remittance.domain.Remittance;
import org.springframework.stereotype.Component;

@Component
public class RemittanceMapper {
    public Remittance mapToDomainEntity(RemittanceRequestJpaEntity entity) {
        return Remittance.generateMember(
                new Remittance.RemittanceRequestId(entity.getRemittanceRequestId()),
                new Remittance.FromMembershipId(entity.getFromMembershipId()),
                new Remittance.ToMembershipId(entity.getToMembershipId()),
                new Remittance.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new Remittance.ToBankName(entity.getToBankName()),
                new Remittance.RemittanceType(entity.getRemittanceType()),
                new Remittance.RemittanceStatus(entity.getRemittanceStatus()),
                new Remittance.Amount(entity.getAmount()),
                new Remittance.Uuid(entity.getUuid())
        );
    }
}
