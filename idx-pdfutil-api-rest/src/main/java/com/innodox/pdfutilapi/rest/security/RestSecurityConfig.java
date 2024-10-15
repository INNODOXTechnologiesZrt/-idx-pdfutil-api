package com.innodox.pdfutilapi.rest.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
@Slf4j
public class RestSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/v3/**", "/swagger**/", "/swagger/**", "/swagger-ui/**", "/openapi.yml").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .addFilter(new BasicAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));

        return http.build();
    }
}
