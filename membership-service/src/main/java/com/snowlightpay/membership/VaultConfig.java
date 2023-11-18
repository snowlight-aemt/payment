package com.snowlightpay.membership;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;
import org.springframework.vault.support.VaultToken;

@Configuration
public class VaultConfig {
    @Value("${spring.cloud.vault.token}")
    private String vaultToken;
    @Value("${spring.cloud.vault.scheme}")
    private String vaultScheme;
    @Value("${spring.cloud.vault.host}")
    private String vaultHost;
    @Value("${spring.cloud.vault.port}")
    private int vaultPort;

    @Bean
    public VaultTemplate vaultTemplate() {
        System.out.println("vaultTemplate");
        VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultHost, vaultPort);
        vaultEndpoint.setScheme(vaultScheme);

        VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, () -> VaultToken.of(vaultToken));
        // /kv-v2/data/encryted/data/dbkey   -->   key : GGGGGGGGGGGGGGGG
        VaultKeyValueOperations vaultKeyValueOperations = vaultTemplate.opsForKeyValue(
                                                        "/kv-v2/data/encryted",
                                                            VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);

        System.out.println(vaultKeyValueOperations.toString());
        VaultResponse dbkey = vaultKeyValueOperations.get("dbkey");
        System.out.println("VaultResponse");
        System.out.println(dbkey.toString());
        String value = dbkey.getData().get("key").toString();
        System.out.println("Value Starter");
        System.out.println(value);
        System.out.println("Value End");
        return vaultTemplate;
    }
}