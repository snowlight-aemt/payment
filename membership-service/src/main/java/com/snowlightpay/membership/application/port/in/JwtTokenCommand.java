package com.snowlightpay.membership.application.port.in;

import com.snowlightpay.common.SelfValidating;
import lombok.Data;

@Data
public class JwtTokenCommand extends SelfValidating<JwtTokenCommand> {
    private String jwtToken;

    public JwtTokenCommand(String jwtToken) {
        this.jwtToken = jwtToken;
        this.validateSelf();
    }
}
