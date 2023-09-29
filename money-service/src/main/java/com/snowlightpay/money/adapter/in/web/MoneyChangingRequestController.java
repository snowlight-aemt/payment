package com.snowlightpay.money.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyCommand;
import com.snowlightpay.money.application.port.in.CreateMemberMoneyUseCase;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.snowlightpay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    @PostMapping("/money/decrease-eda")
    public ResponseEntity<MoneyChangingResultDetail> decreaseMoneyRequest(@RequestBody IncreaseMoneyRequest request) {
        // 테스트를 위해서 임시 구현
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(request.getTargetMembershipId(),
                                                            request.getChangingMoneyAmount() * -1);

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
    public void createMemberMoney(@RequestBody CreateMemberMoney createMemberMoney) {
        log.info("createMemberMoney - {}", createMemberMoney.toString());
        CreateMemberMoneyCommand command = new CreateMemberMoneyCommand(createMemberMoney.getMembershipId());
        createMemberMoneyUseCase.createMemberMoney(command);
    }

    @PostMapping("/money/increase-eda")
    public void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyRequest request) {
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(request.getTargetMembershipId(),
                                                                                request.getChangingMoneyAmount());

        this.increaseMoneyRequestUseCase.increaseMoneyChangingRequestByEvent(command);
    }
}
