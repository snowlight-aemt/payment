package com.snowlightpay.money.aggregation.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.money.aggregation.application.port.in.GetMoneySumByAddressCommand;
import com.snowlightpay.money.aggregation.application.port.in.GetMoneySumByAddressUseCase;
import com.snowlightpay.money.aggregation.domain.MoneySum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
public class GetMoneySumByAddressController {
    private final GetMoneySumByAddressUseCase getMoneySumByAddressUseCase;
    @GetMapping("/money-aggregation/sum")
    public ResponseEntity<MoneySum> getMoneySumByAddress(
                                        @ParameterObject @ModelAttribute GetMoneySumByAddressRequest request) {
        GetMoneySumByAddressCommand command = new GetMoneySumByAddressCommand(request.getAddress());
        return ResponseEntity.ok(getMoneySumByAddressUseCase.getMoneySumByAddress(command));
    }

}
