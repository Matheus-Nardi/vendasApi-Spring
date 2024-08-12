package com.mafn.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(custum -> custum.disable())
                .authorizeHttpRequests(customizer -> {
                    customizer.requestMatchers(HttpMethod.GET, "/produtos").permitAll();
                    customizer.requestMatchers(HttpMethod.POST, "/clientes").permitAll();
                    customizer.requestMatchers(HttpMethod.POST, "/pedidos").authenticated();
                    customizer.requestMatchers("/clientes/**").authenticated();
                    customizer.requestMatchers("/pedidos/**").authenticated();
                    customizer.requestMatchers("/produtos/**").hasRole("ADMIN");
                    customizer.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails clienteComum = User
                    .builder()
                    .username("cliente")
                    .password(passwordEncoder().encode("123"))
                    .roles("USER")
                    .build();

        UserDetails admComum = User.builder()
                                .username("adm")
                                .password(passwordEncoder().encode("admin"))
                                .roles("USER" , "ADMIN")
                                .build();

        return new InMemoryUserDetailsManager(admComum, clienteComum);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
