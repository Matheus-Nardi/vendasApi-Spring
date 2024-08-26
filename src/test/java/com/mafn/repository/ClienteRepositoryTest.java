package com.mafn.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.mafn.dto.ClienteRequestDTO;
import com.mafn.models.Cliente;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("Should get Cliente sucessfully from DB by cpf")
    void findClienteByCpfCase1() {
        String cpf = "98177670000";
        ClienteRequestDTO cliente = new ClienteRequestDTO();
        cliente.setNome("Matheus Teste");
        cliente.setCpf(cpf);
        cliente.setSenha("123");
        createUser(cliente);

        Optional<Cliente> result = clienteRepository.findByCpf(cpf);
        assertThat(result.isPresent()).isTrue();
    }

    
    @Test
    @DisplayName("Should not get Cliente from DB when cliente not exists")
    void findClienteByCpfCase2() {
        String cpf = "98177670000";
        ClienteRequestDTO cliente = new ClienteRequestDTO();
        cliente.setNome("Matheus Teste");
        cliente.setCpf(cpf);
        cliente.setSenha("123");
        //createUser(cliente); Simulando que não há cliente

        Optional<Cliente> result = clienteRepository.findByCpf(cpf);
        assertThat(result.isEmpty()).isTrue();
    }
    @Test
    @DisplayName("Should get a list of cliente from DB")
    void findClienteByNomeCase1() {
        ClienteRequestDTO cliente1 = new ClienteRequestDTO();
        cliente1.setNome("Matheus");
        cliente1.setCpf("98177670000");
        cliente1.setSenha("123");
        createUser(cliente1);
        ClienteRequestDTO cliente2 = new ClienteRequestDTO();
        cliente2.setNome("Matheus");
        cliente2.setCpf("04313203060");
        cliente2.setSenha("1234");
        createUser(cliente2);
        List<Cliente> result = clienteRepository.encontrarPorNome("Matheus");
        assertEquals(2, result.size());
    }
    @Test
    @DisplayName("Should return  a list of cliente with size equals 0")
    void findClienteByNomeCase2() {
        ClienteRequestDTO cliente1 = new ClienteRequestDTO();
        cliente1.setNome("Matheus");
        cliente1.setCpf("98177670000");
        cliente1.setSenha("123");
        List<Cliente> result = clienteRepository.encontrarPorNome("Matheus");
        assertEquals(0, result.size());
    }

    private Cliente createUser(ClienteRequestDTO data) {
        Cliente newCliente = Cliente.builder().nome(data.getNome())
                .cpf(data.getCpf())
                .senha(data.getSenha())
                .build();
        entityManager.persist(newCliente);
        return newCliente;
    }
}
