package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MoneyChangingRequestMapper {
    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity entity) {
        return MoneyChangingRequest.generateMember(
            new MoneyChangingRequest.MoneyChangingRequestId(entity.getMoneyChangingRequestId()+""),
            new MoneyChangingRequest.TargetMembershipId(entity.getTargetMembershipId()+""),
            new MoneyChangingRequest.MoneyChangingAmount(entity.getMoneyChangingAmount()),
            new MoneyChangingRequest.MoneyChangingStatus(entity.getMoneyChangingStatus()),
            new MoneyChangingRequest.ChangingType(entity.getChangingType()),
            new MoneyChangingRequest.Uuid(UUID.fromString(entity.getUuid()))
        );
    }
}
