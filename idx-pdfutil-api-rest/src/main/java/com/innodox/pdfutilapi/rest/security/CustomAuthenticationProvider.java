package com.innodox.pdfutilapi.rest.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final String idxUser;
    private final String idxPassword;

    public CustomAuthenticationProvider(@Value("${idx.user}") String idxUser, @Value("${idx.password}") String idxPassword) {
        this.idxUser = idxUser;
        this.idxPassword = idxPassword;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {

        if (authentication == null) {
            log.warn("Authentication is null!");
            return null;
        }

        String uname = String.valueOf(authentication.getName());
        String upassw = String.valueOf(authentication.getCredentials());

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_IDX"));

        if (!idxUser.equals(uname) || !idxPassword.equals(upassw)) {
            log.warn("Wrong username or password!");
            return null;
        }

        return new UsernamePasswordAuthenticationToken(uname, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
