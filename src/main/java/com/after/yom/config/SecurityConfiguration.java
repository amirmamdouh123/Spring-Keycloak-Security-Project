package com.after.yom.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Configuration
//@EnableMethodSecurity

@Configuration
@EnableWebSecurity
@SecurityScheme(
        name = "MyKeycloak",
        openIdConnectUrl = "http://localhost:8080/realms/amir-realm/.well-known/openid-configuration",
        scheme = "bearer",
        type= SecuritySchemeType.OPENIDCONNECT,
        in= SecuritySchemeIn.HEADER
)
@EnableAspectJAutoProxy
public class SecurityConfiguration  {

//    @Autowired
//    JwtAuthConverter jwtAuthConverter;




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->
                        request.requestMatchers("/swagger-ui/**","/v3/api-docs/**","api/anybody").permitAll()
                                .requestMatchers("/api/manager/**").hasRole("manager")
                                .requestMatchers("/api/admin/**").hasRole("admin")
                                .requestMatchers("keycloak/**").hasAnyRole("admin","manager")
                                .anyRequest().authenticated());
//                http.oauth2ResourceServer(t->{
//                    t.opaqueToken(Customizer.withDefaults());})

//        http.addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
        http.oauth2ResourceServer(oauth->
                        oauth.jwt(jwtConfigurer->
                                jwtConfigurer.jwtAuthenticationConverter(myJwtAuthenticationConverter())));
//        http.oauth2ResourceServer(j-> j.jwt(jwtConv -> jwtConv.jwtAuthenticationConverter(jwtAuthenticationConverter()))); //eshta 3la alroles
        http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest httpRequest) {
                try {
                    return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"),
                            PolicyEnforcerConfig.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


//
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromOidcIssuerLocation("http://localhost:8080/realms/amir-realm");
    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager(User.builder().username("amir").password(passwordEncoder().encode("amir")).roles("ADMIN").build());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(){
//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler=new DefaultMethodSecurityExpressionHandler();
//        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
//        return defaultMethodSecurityExpressionHandler;
//    }

//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtAuthenticationConverter jc =new JwtAuthenticationConverter();
//        JwtGrantedAuthoritiesConverter roles=new JwtGrantedAuthoritiesConverter();
//        roles.setAuthorityPrefix("ROLE_");  //insert before each role "ROLE_" -> "ROLE_firstRole"
//        roles.setAuthoritiesClaimName("roles");
//        jc.setJwtGrantedAuthoritiesConverter(roles);
//
//        return jc;
//    }


    @Bean
    public JwtAuthenticationConverter myJwtAuthenticationConverter(){
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter authorties =new JwtGrantedAuthoritiesConverter();
        authorties.setAuthorityPrefix("ROLE_");  //ROLE_myRole
        authorties.setAuthoritiesClaimName("roles");
        jwtConverter.setJwtGrantedAuthoritiesConverter(authorties);
        return jwtConverter;
    }

//    public JwtAuthConverter jwtAuthConverter(){
//        return new JwtAuthConverter();
//    }








//    @Bean
//    public DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler(){
//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler=new DefaultMethodSecurityExpressionHandler();
//        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
//        return defaultMethodSecurityExpressionHandler;
//    }

}


