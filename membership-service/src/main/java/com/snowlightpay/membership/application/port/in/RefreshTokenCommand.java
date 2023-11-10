package com.snowlightpay.membership.application.port.in;

import lombok.Data;

@Data
public class RefreshTokenCommand {
    private String refreshToken;
    public RefreshTokenCommand(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
