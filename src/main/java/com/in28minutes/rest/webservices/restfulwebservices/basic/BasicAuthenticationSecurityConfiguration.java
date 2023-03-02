package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {

    //filter chain
    //authenticate all requests
    //basic auth
    //disabling csrf
    //stateless REST API
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        try {
            return
                    http
                            .authorizeHttpRequests(
                            auth -> auth
                                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                    .anyRequest().authenticated()
                            )
                            .httpBasic(Customizer.withDefaults())
                            .sessionManagement(
                                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                            .csrf().disable()
                            .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
