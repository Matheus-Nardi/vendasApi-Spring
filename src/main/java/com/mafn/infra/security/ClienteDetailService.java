package com.mafn.infra.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mafn.models.Cliente;
import com.mafn.service.impl.ClienteServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteDetailService implements UserDetailsService {

    private final ClienteServiceImpl clienteService;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Cliente cliente = clienteService.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado com o CPF: " + cpf));
        return new User(cliente.getUsername(),cliente.getPassword(),cliente.getAuthorities());
    }

}
