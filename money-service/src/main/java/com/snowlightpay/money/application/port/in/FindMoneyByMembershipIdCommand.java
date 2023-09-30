package com.snowlightpay.money.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class FindMoneyByMembershipIdCommand extends SelfValidating<FindMoneyByMembershipIdCommand> {
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private List<String> membershipIds;

    public FindMoneyByMembershipIdCommand(List<String> membershipIds) {
        this.membershipIds = membershipIds;

        this.validateSelf();
    }
}
