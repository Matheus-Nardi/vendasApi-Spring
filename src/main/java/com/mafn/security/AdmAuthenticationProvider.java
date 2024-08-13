package com.mafn.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AdmAuthenticationProvider implements AuthenticationProvider {

    private final String  LOGIN = "adm";
    private final String  PASSWORD = "admMaster";
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        if(LOGIN.equals(login) && PASSWORD.equals(password)){
            return new UsernamePasswordAuthenticationToken("Logado como adm master", null , List.of(new SimpleGrantedAuthority("ADMIN")));
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
