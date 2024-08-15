package com.mafn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mafn.exception.NotFoundException;
import com.mafn.models.Cliente;
import com.mafn.repository.ClienteRepository;
import com.mafn.service.SimpleCrud;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClienteServiceImpl implements SimpleCrud<Cliente> {

    private PasswordEncoder passwordEncoder;
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository,PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void save(Cliente entity) {
        log.info("Salvando um novo cliente.");
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));
        clienteRepository.save(entity);
    }

    @Override
    public Cliente findById(Integer id) {
        log.info("Buscando por um cliente de id {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar um cliente com id: " + id));
    }

    
    public Optional<Cliente> findByCpf(String cpf) {
        log.info("Buscando por um cliente de cpf {}", cpf);
        return clienteRepository.findByCpf(cpf);
                
    }

    @Override
    public List<Cliente> findAll() {
        log.info("Retornando todos os clientes.");
        return clienteRepository.findAll();
    }

    @Override
    public List<Cliente> findByFilter(Cliente entityFilter) {
        log.info("Retornando clientes filtrados.");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<Cliente> clienteExample = Example.of(entityFilter, exampleMatcher);
        return clienteRepository.findAll(clienteExample);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        log.info("Deletando cliente de id {}", id);
         Cliente cliente =  clienteRepository.findById(id)
                                                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar um cliente com id: " + id));
         clienteRepository.delete(cliente);
         log.info("Cliente de id {} deletado com sucesso", id);               
    }

    @Override
    @Transactional
    public void update(Integer id, Cliente entityToUpdate) {
        log.info("Atualizando cliente de id {}", id);
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty())
            throw new NotFoundException("Não foi possível encontrar um cliente com id: " + id);
        Cliente clienteFromDb = clienteOptional.get();
        log.info("Cliente de id {} encontrado", id);

        if (entityToUpdate.getNome() != null) {
            clienteFromDb.setNome(entityToUpdate.getNome());
        }
        if (entityToUpdate.getCpf() != null) {
            clienteFromDb.setCpf(entityToUpdate.getCpf());
        }

        clienteRepository.save(clienteFromDb);
        log.info("Cliente de id {} atualizado com sucesso", id);

    }


    public boolean existsByNome(String nome){
        return clienteRepository.existsByNome(nome);
    }

}
