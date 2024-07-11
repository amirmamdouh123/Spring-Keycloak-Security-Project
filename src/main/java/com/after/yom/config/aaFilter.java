//package com.after.yom.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class aaFilter extends OncePerRequestFilter {
//
//    @Autowired
//    JwtDecoder jwtDecoder;
////    @Autowired
////    JwtAuthenticationConverter jwtAuthenticationConverter;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token="";
//        if(request.getHeader("Authorization")!=null){
//            token =request.getHeader("Authorization").replace("Bearer ", "");
//        }
//        else{
//            System.out.println("jwt is null");
//            return;
//        }
//        System.out.println(token);
//        Jwt jwt=jwtDecoder.decode(token);
//        System.out.println(jwtAuthenticationConverter.convert(jwt).getAuthorities());
//    filterChain.doFilter(request,response);
//        return ;
//    }
//}
