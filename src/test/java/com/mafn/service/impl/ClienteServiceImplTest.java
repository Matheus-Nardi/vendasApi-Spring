package com.mafn.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mafn.exception.NotFoundException;
import com.mafn.models.Cliente;
import com.mafn.repository.ClienteRepository;

public class ClienteServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ClienteRepository clienteRepository;

    @Autowired
    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should delete a client successfully")
    void testDeleteByIdCase1() {
        Cliente cliente = new Cliente();
        cliente.setId(1);

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        clienteServiceImpl.deleteById(1);

        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    @DisplayName("Should throw an exception if the client does not exist")
    void testDeleteByIdCase2() {
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clienteServiceImpl.deleteById(1));

        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(0)).delete(cliente);
    }

    @Test
    @DisplayName("Should return all clients from the repository")
    void testFindAllCase1() {
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteServiceImpl.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(cliente1));
        assertTrue(result.contains(cliente2));

        verify(clienteRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Should return an empty list of clientes from the repository")
    void testFindAllCase2() {

        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        List<Cliente> result = clienteServiceImpl.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(clienteRepository, times(1)).findAll();

    }

    @Test
    void testFindByCpfCase1() {
        String cpf = "98177670000";
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteServiceImpl.findByCpf(cpf);
        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());

        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Should return empty when no client is found by CPF")
    void testFindByCpfCase2() {
        String cpf = "98177670000";

        when(clienteRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<Cliente> result = clienteServiceImpl.findByCpf(cpf);
        assertTrue(result.isEmpty());

        verify(clienteRepository, times(1)).findByCpf(cpf);
    }

    @Test
    void testPasswordEncoder() {
        String senha = "password";
        String senhaCriptografada = "encryptedPassword";
        Cliente cliente = new Cliente();
        cliente.setSenha(senha);

        when(passwordEncoder.encode(senha)).thenReturn(senhaCriptografada);
        clienteServiceImpl.save(cliente);
        verify(passwordEncoder, times(1)).encode(senha);
        assertEquals(senhaCriptografada, cliente.getSenha());
    }

}
