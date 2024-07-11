package com.after.yom.realm.keycloakController;

import com.after.yom.realm.dto.User;
import com.after.yom.realm.securirty.KeycloakSecurityUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("keycloak")
@SecurityRequirement(name = "Keycloak")
public class UserResource {

    @Autowired
    KeycloakSecurityUtil keycloakSecurityUtil;

    @Value("${realm}")
    String realm;


    @GetMapping("users")
    public List<User> getUsers(){  //m7tagin n connect b keycloak realm 34an ngib mno users
        Keycloak keycloak= keycloakSecurityUtil.getKeycloakInstance();

        List<UserRepresentation> userRepresentationList =keycloak.realm(realm).users().list();
        return mapToUsers(userRepresentationList);
    }

    @PostMapping
    @RequestMapping( path = "user" , method = RequestMethod.POST)
    public Response createUser(User user){  //m7tagin n connect b keycloak realm 34an ngib mno users
        Keycloak keycloak= keycloakSecurityUtil.getKeycloakInstance();
        UserRepresentation userRep=mapToUserRep(user);
        return keycloak.realm(realm).users().create(userRep);
    }

    public User mapToUser(UserRepresentation userRepresentation){
        return User.builder()
                .email(userRepresentation.getEmail())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .username(userRepresentation.getUsername())
                .build();
    }

    public List<User> mapToUsers(List<UserRepresentation> userRepresentationList){
        List<User> users=new ArrayList<>();
        userRepresentationList.forEach((userRep)->{
            User user=mapToUser(userRep);
            users.add(user);
        });
        return users;
    }

    public UserRepresentation mapToUserRep(User user){
        UserRepresentation userRepresentation=new  UserRepresentation();
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        List<CredentialRepresentation> creds=new ArrayList<>();
        CredentialRepresentation cred=new CredentialRepresentation();
        cred.setValue(user.getPassword());
        cred.setTemporary(false);
        creds.add(cred);
        userRepresentation.setCredentials(creds);
        return userRepresentation;
    }

    public List<UserRepresentation> mapToUserRep(List<User> userList){
        List<UserRepresentation> userReps=new ArrayList<>();
        userList.forEach((user)->{
            UserRepresentation userRep=mapToUserRep(user);
            userReps.add(userRep);
        });
        return userReps;
    }



}
