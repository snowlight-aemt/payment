package com.snowlightpay.membership.adapter.out.vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultTemplate;

@Component
public class VaultAdapter {
    @Autowired
    public VaultAdapter(VaultTemplate vaultTemplate) {
        System.out.println(vaultTemplate);
    }
}
