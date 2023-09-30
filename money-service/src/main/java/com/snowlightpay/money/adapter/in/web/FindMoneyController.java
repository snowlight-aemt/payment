package com.snowlightpay.money.adapter.in.web;

import com.snowlightpay.common.WebAdapter;
import com.snowlightpay.money.application.port.in.FindMoneyByMembershipIdCommand;
import com.snowlightpay.money.application.port.in.FindMoneyUseCase;
import com.snowlightpay.money.domain.MemberMoney;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMoneyController {
    private final FindMoneyUseCase findMoneyUseCase;

    @GetMapping("/money/member-money")
    public ResponseEntity<List<MemberMoney>> findMemberMoneyListByMembershipIds(
                                        @ParameterObject @ModelAttribute FindMoneyByMembershipIdRequest request) {
        FindMoneyByMembershipIdCommand command = new FindMoneyByMembershipIdCommand(request.getMembershipIds());
        List<MemberMoney> memberMoneyList = findMoneyUseCase.findMemberMoneyListByMembershipIds(command.getMembershipIds());
        return ResponseEntity.ok(memberMoneyList);
    }
}
