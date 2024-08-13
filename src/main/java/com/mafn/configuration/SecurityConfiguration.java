package com.mafn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mafn.security.CustomFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider provider , CustomFilter custumFiler)
            throws Exception {

        return httpSecurity
                .csrf(custum -> custum.disable())
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers("/clientes/**").hasAnyRole("USER", "ADMIN");
                    customizer.requestMatchers("/pedidos/**").hasAnyRole("USER", "ADMIN");
                    customizer.requestMatchers("/produtos/**").hasRole("ADMIN");
                    customizer.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(provider)
                .addFilterBefore(custumFiler, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService detailsService(){
        UserDetails user =  User.builder()
                    .username("cliente")
                    .password(passwordEncoder().encode("erro"))
                    .roles("USER","ADMIN")
                    .build(); 

        return new InMemoryUserDetailsManager(user);
    }
}
