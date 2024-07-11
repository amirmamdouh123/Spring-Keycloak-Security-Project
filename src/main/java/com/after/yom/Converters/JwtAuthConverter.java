//package com.after.yom.Converters;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.protobuf.Internal;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.stereotype.Component;
//import org.springframework.core.convert.converter.Converter;
//
//import java.util.*;
//
//@Component
//public class JwtAuthConverter implements Converter<Jwt,AbstractAuthenticationToken> {
//    @Override
//    public AbstractAuthenticationToken convert(Jwt jwt) {
//        Collection<GrantedAuthority> roles =extractRolesClaim(jwt);
//        return new JwtAuthenticationToken(jwt,roles);
//    }
//
////    public Collection<GrantedAuthority> extractRolesClaim(Jwt jwt){
////        if(jwt.getClaim("realm_access") !=null){
////            Map<String , Object> realmAccess=jwt.getClaim("realm_access");
////            ObjectMapper mapper= new ObjectMapper();
////            List<String> keycloakRoles= mapper.convertValue(realmAccess.get("roles"), new TypeReference<List<String>>() {
////            });
////            List<GrantedAuthority> roles =new ArrayList<>();
////
////            for(String keycloakRole:keycloakRoles){
////                System.out.println("ROLE_"+keycloakRole);
////                roles.add( new SimpleGrantedAuthority("ROLE_"+keycloakRole));
////            }
////            return roles;
////        }
////        return new ArrayList<>();
////    }
//
//    public Collection<GrantedAuthority> extractRolesClaim(Jwt jwt){
//
//        if(jwt.getClaim("realm_access")!=null){
//
//            Map<String ,Object>  x= jwt.getClaim("realm_access");
//            ObjectMapper mapper= new ObjectMapper();
//            List<String> roles = mapper.convertValue(x.get("roles"),new TypeReference<List<String>>(){});
//
//            Collection<GrantedAuthority> authorities =new ArrayList<>();
//
//            roles.forEach((role)-> authorities.add(new SimpleGrantedAuthority("ROLE_"+role)));
//            return authorities;
//        }
//     return new ArrayList<>();
//    }
//
//
//}
