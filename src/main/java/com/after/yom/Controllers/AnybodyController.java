package com.after.yom.Controllers;

import com.sun.net.httpserver.Headers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/anybody")
@SecurityRequirement(name = "MyKeycloak")
public class AnybodyController {

//    @Autowired
//    EmployeeService employeeService;

//    @Autowired
//    JwtAuthenticationConverter jwtAuthenticationConverter;

//    @GetMapping
//    public Collection<GrantedAuthority> getRoles(){
//
//        String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJROVVJQXIxYjVVX2dPelhPV2dKd2d5MnJtOUxnanZZZllVeDNsM0MyVC1vIn0.eyJleHAiOjE3MTYwMzYxOTEsImlhdCI6MTcxNjAzNTg5MSwianRpIjoiZWYxMDJiOTMtNWZiNS00MTVlLThiNGQtOTlhMDQyMzA3MTIzIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9hbWlyLXJlYWxtIiwiYXVkIjpbInJlYWxtLW1hbmFnZW1lbnQiLCJhY2NvdW50Il0sInN1YiI6IjA5NzRmNDQ2LTNhYjAtNDhmMC05YzRhLTBkNWMzY2JlYTA1NSIsInR5cCI6IkJlYXJlciIsImF6cCI6InNwcmluZ2Jvb3QtYmUiLCJzZXNzaW9uX3N0YXRlIjoiOGEzZWIwYzEtOGYwMS00OTlhLThiZjEtODUwMTNjNzc5MGI0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWFtaXItcmVhbG0iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctaWRlbnRpdHktcHJvdmlkZXJzIiwidmlldy1yZWFsbSIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwicmVhbG0tYWRtaW4iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoibWljcm9wcm9maWxlLWp3dCBwcm9maWxlIGVtYWlsIiwic2lkIjoiOGEzZWIwYzEtOGYwMS00OTlhLThiZjEtODUwMTNjNzc5MGI0IiwidXBuIjoiYW1pciIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWFtaXItcmVhbG0iXSwibmFtZSI6ImFtaXIgYWRtaW4iLCJncm91cHMiOlsib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIiwiZGVmYXVsdC1yb2xlcy1hbWlyLXJlYWxtIl0sInByZWZlcnJlZF91c2VybmFtZSI6ImFtaXIiLCJnaXZlbl9uYW1lIjoiYW1pciIsImZhbWlseV9uYW1lIjoiYWRtaW4iLCJlbWFpbCI6ImFtaXIubWFtLmhleUBnbWFpbC5jb20ifQ.0dLYqGoh-23PpkgoTaPrlZoj1_D2B9YMTqcYdDhfTawr1vc7J3mmCJcPg1XDISRt7XMoN8_OBKX9s6W04eLJcQjGfaH4m4C7wJAiO7zc2xSaIIskNPblgHQ9g6p7nT5K_vxszSvDouGj9CwxLwQroRHuukX022Xhg_DxnEaKcZsIZ5lWYyPimA-ogRlxSsCEkyfyBV9Z2FBsRhrw4zysOsNf4_7aoJBS2_eHmp2psT-DLt4VLTRUJZsp82q64cgUYt2kV73T3r4YK4uzEVtnwYVF51H1YJab4ly4RfSj-WZMzjSnas8eNslX0M-cgl1Y7WZyBu1jeS__FyIkB95syQ";
//
//        Instant issuedAt = Instant.parse("2024-01-20T00:00:00Z");
//        Instant expiresAt = Instant.parse("2024-05-18T00:00:00Z");
//
//        Map<String, Object> headers = new HashMap<>();
//
//
//        headers.put("alg", "RS256");
//        headers.put("typ", "JWT");
//
//        // Populate the claims map with all provided claims information
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("exp", 1716034888);
//        claims.put("iat", 1716034588);
//        claims.put("jti", "6dcb0f9a-a846-4599-9c83-cdf53e3c19e1");
//        claims.put("iss", "http://localhost:8080/realms/amir-realm");
//        claims.put("aud", new String[]{"realm-management", "account"});
//        claims.put("sub", "0974f446-3ab0-48f0-9c4a-0d5c3cbea055");
//        claims.put("typ", "Bearer");
//        claims.put("azp", "springboot-be");
//        claims.put("session_state", "037001be-80cc-4946-8b28-08618e3aecbc");
//        claims.put("acr", "1");
//        claims.put("allowed-origins", new String[]{"*"});
//
//        Map<String, Object> realmAccess = new HashMap<>();
//        realmAccess.put("roles", new String[]{"offline_access", "admin", "uma_authorization", "default-roles-amir-realm"});
//        claims.put("realm_access", realmAccess);
//
//        Map<String, Object> realmManagement = new HashMap<>();
//        realmManagement.put("roles", new String[]{"view-identity-providers", "view-realm", "manage-identity-providers", "impersonation", "realm-admin", "create-client", "manage-users", "query-realms", "view-authorization", "query-clients", "query-users", "manage-events", "manage-realm", "view-events", "view-users", "view-clients", "manage-authorization", "manage-clients", "query-groups"});
//
//        Map<String, Object> account = new HashMap<>();
//        account.put("roles", new String[]{"manage-account", "manage-account-links", "view-profile"});
//
//        Map<String, Object> resourceAccess = new HashMap<>();
//        resourceAccess.put("realm-management", realmManagement);
//        resourceAccess.put("account", account);
//        claims.put("resource_access", resourceAccess);
//
//        claims.put("scope", "profile email");
//        claims.put("sid", "037001be-80cc-4946-8b28-08618e3aecbc");
//        claims.put("email_verified", true);
//        claims.put("roles", new String[]{"offline_access", "admin", "uma_authorization", "default-roles-amir-realm"});
//        claims.put("name", "amir admin");
//        claims.put("preferred_username", "amir");
//        claims.put("given_name", "amir");
//        claims.put("family_name", "admin");
//        claims.put("email", "amir.mam.hey@gmail.com");
//
//
//
//        Jwt jwt = new Jwt(token, issuedAt, expiresAt, headers, claims);
//
//
//
//
////        return jwtAuthenticationConverter.convert(jwt).getAuthorities();
//    }





    @GetMapping
    public String amir(){
        return "anybody only";
    }
//
//    @PostMapping
//    public ResponseEntity<?> insertEmployee(@RequestBody Employee employee){
//        try {
//            return ResponseEntity.ok(employeeService.insertEmployee(employee));
//
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<?> getEmp(@PathVariable("id") Long id){
//        try {
//            return ResponseEntity.ok(employeeService.getEmployeeById(id));
//
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }





//
//    @GetMapping("{id}")
//    public ResponseEntity<?> getProjection(@PathVariable("id") Long id){
//        try {
//            return ResponseEntity.ok(employeeService.getProjectionById(id));
//
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }

}
