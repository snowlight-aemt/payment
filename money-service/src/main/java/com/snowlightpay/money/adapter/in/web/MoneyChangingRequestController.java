package com.snowlightpay.money.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyCommand;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyUseCase;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.snowlightpay.money.domain.MemberMoney;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class MoneyChangingRequestController {
    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;

    private final MoneyChangingResultDetailMapper moneyChangingResultDetailMapper;

    @PostMapping("/money/increase")
    public ResponseEntity<MoneyChangingResultDetail> increaseMoneyRequest(@RequestBody IncreaseMoneyRequest request) {
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(request.getTargetMembershipId(),
                                                                                request.getChangingMoneyAmount());

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyChangingRequest(command);
        return ResponseEntity.ok(moneyChangingResultDetailMapper.mapToMoneyChangingResultDetail(moneyChangingRequest));
    }

    @PostMapping("/money/increase-async")
    public ResponseEntity<MoneyChangingResultDetail> increaseMoneyRequestAsync(@RequestBody IncreaseMoneyRequest request) {
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(request.getTargetMembershipId(),
                                                                                request.getChangingMoneyAmount());

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyChangingRequestAsync(command);
        return ResponseEntity.ok(moneyChangingResultDetailMapper.mapToMoneyChangingResultDetail(moneyChangingRequest));
    }

    @PostMapping("/money/create-member-money")
    public void createMemberMoney(CreateMemberMoney createMemberMoney) {
        CreateMemberMoneyCommand command = new CreateMemberMoneyCommand(createMemberMoney.getMemberShipId());
        createMemberMoneyUseCase.createMemberMoney(command);
    }
}
