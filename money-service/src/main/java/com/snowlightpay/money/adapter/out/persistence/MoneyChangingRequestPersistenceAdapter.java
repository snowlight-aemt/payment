package com.snowlightpay.money.adapter.out.persistence;

import com.snowlightpay.common.PersistenceAdapter;
import com.snowlightpay.money.application.port.out.CreateMemberMoneyPort;
import com.snowlightpay.money.application.port.out.GetMemberMoneyPort;
import com.snowlightpay.money.application.port.out.IncreaseMoneyRequestPort;
import com.snowlightpay.money.domain.MemberMoney;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyRequestPort, CreateMemberMoneyPort, GetMemberMoneyPort {
    private final MoneyChangingRequestRepository moneyChangingRequestRepository;
    private final MemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChanging(MoneyChangingRequest.TargetMembershipId targetMembershipId,
                                                             MoneyChangingRequest.MoneyChangingAmount moneyChangingAmount,
                                                             MoneyChangingRequest.ChangingType changingType,
                                                             MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus) {

        return this.moneyChangingRequestRepository.save(new MoneyChangingRequestJpaEntity(
                Long.parseLong(targetMembershipId.getTargetMembershipId()),
                moneyChangingAmount.getMoneyChangingAmount(),
                changingType.getChangingType(),
                moneyChangingStatus.getMoneyChangingStatus(),
                UUID.randomUUID()
        ));
    }

    @Override
    public MoneyChangingRequestJpaEntity modifyMoneyChanging(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
        return this.moneyChangingRequestRepository.save(moneyChangingRequestJpaEntity);
    }

    @Override
    public MemberMoneyJpaEntity increaseMemberMoney(MemberMoney.MembershipId membershipId,
                                                    MemberMoney.Balance balance) {
        long id = Long.parseLong(membershipId.getMembershipId());
        MemberMoneyJpaEntity memberMoneyJpaEntity = this.memberMoneyRepository.findByMembershipId(id)
                                                        .stream().findFirst().orElseGet(() ->
                                                            new MemberMoneyJpaEntity(id, 0, ""));
        memberMoneyJpaEntity.increase(balance.getBalance());

        memberMoneyRepository.save(memberMoneyJpaEntity);
        return memberMoneyJpaEntity;
    }

    @Override
    public void createMemberMoney(MemberMoney.MembershipId membershipId, MemberMoney.MoneyAggregateIdentifier aggregateIdentifier) {
        MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
                Long.parseLong(membershipId.getMembershipId()),
                0,
                aggregateIdentifier.getAggregateIdentifier()

        );
        this.memberMoneyRepository.save(entity);
    }

    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId memberMoneyId) {
        return this.memberMoneyRepository.findByMembershipId(Long.parseLong(memberMoneyId.getMembershipId()))
                                            .stream().findFirst().orElseThrow(IllegalAccessError::new);
    }
}
