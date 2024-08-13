package com.mafn.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("x-secret");

        if(header != null){
            if(header.equals("secr3t")){
                Authentication authentication = new UsernamePasswordAuthenticationToken("Acesso secreto via header", null, List.of(new SimpleGrantedAuthority("ADMIN")) );

               SecurityContext securityContext =  SecurityContextHolder.getContext();
               securityContext.setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
