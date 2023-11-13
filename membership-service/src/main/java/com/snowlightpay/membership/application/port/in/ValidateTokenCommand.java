package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Data;

@Data
public class ValidateTokenCommand extends SelfValidating<ValidateTokenCommand> {
    private String jwtToken;

    public ValidateTokenCommand(String jwtToken) {
        this.jwtToken = jwtToken;
        this.validateSelf();
    }
}
