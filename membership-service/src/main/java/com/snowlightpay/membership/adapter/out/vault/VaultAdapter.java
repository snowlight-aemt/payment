package com.snowlightpay.membership.adapter.out.vault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

@Component
public class VaultAdapter {
    AESProvider encryptor;

    @Autowired
    public VaultAdapter(VaultTemplate vaultTemplate) {
        // /kv-v2/data/encryted/data/dbkey   -->   key : GGGGGGGGGGGGGGGG
        VaultKeyValueOperations vaultKeyValueOperations = vaultTemplate.opsForKeyValue(
                                                        "/kv-v2/data/encryted",
                                                            VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);

        String key = vaultKeyValueOperations.get("dbkey").getData().get("key").toString();
        this.encryptor = new AESProvider(key);
    }

    public String encrypt(String plainText) {
        try {
            return this.encryptor.encrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decrypt(String encryptedText) {
        try {
            return this.encryptor.decrypt(encryptedText);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
