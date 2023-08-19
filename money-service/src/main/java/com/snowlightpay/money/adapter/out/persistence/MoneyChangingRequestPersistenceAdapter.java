package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.common.PersistenceAdapter;
import com.snowlightpay.money.application.port.out.IncreaseMoneyRequestPort;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyRequestPort {
    private final MoneyChangingRequestRepository repository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChanging(MoneyChangingRequest.TargetMembershipId targetMembershipId,
                                                             MoneyChangingRequest.MoneyChangingAmount moneyChangingAmount,
                                                             MoneyChangingRequest.ChangingType changingType,
                                                             MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus) {

        return this.repository.save(new MoneyChangingRequestJpaEntity(
                Long.parseLong(targetMembershipId.getTargetMembershipId()),
                moneyChangingAmount.getMoneyChangingAmount(),
                changingType.getChangingType(),
                moneyChangingStatus.getMoneyChangingStatus(),
                UUID.randomUUID()
        ));
    }
}
