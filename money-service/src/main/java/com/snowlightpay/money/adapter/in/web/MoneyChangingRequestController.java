package com.snowlightpay.money.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.snowlightpay.money.application.port.in.IncreaseMoneyRequestUseCase;
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
    @PostMapping("/backing/account/register")
    public ResponseEntity<MoneyRequestResultDetail> hello(@RequestBody IncreaseMoneyRequest request) {
        IncreaseMoneyRequestCommand command = new IncreaseMoneyRequestCommand(request.getTargetMembershipId(),
                                                                                request.getChangingMoneyAmount());

        // ToDo Mapping
        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyChangingRequest(command);
        return ResponseEntity.ok(new MoneyRequestResultDetail());
    }
}
