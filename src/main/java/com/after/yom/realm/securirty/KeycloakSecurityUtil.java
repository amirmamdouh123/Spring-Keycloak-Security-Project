package com.after.yom.realm.securirty;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    Keycloak keycloak;

    @Value("${auth-server-url}")
    private String keycloakURL;

    @Value("${realm}")
    private String realm;

    @Value("${client-id}")
    private String client_id;



    @Value("${grant-type}")
    private String grant_type;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    public Keycloak getKeycloakInstance(){
        if (keycloak==null){
            keycloak= KeycloakBuilder.builder()
                    .serverUrl(keycloakURL)
                    .realm(realm)
                    .clientId(client_id)
                    .grantType(grant_type)
                    .username(username)
                    .password(password).build();
        }
        return keycloak;
    }

}
