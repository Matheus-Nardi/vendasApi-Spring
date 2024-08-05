package com.mafn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.mafn.models.Cliente;
import com.mafn.repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

   
    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

   
    public void deletar(Integer id){
        if(this.obterClientePorId(id).isPresent())
            clienteRepository.deleteById(id);

    }

 
    public boolean atualizar(Cliente cliente , Integer id){
        Optional<Cliente> clienteById = clienteRepository.findById(id);
        if(clienteById.isPresent()){
            Cliente clienteFromDB = clienteById.get();
            clienteFromDB.setNome(cliente.getNome());
            clienteRepository.save(clienteFromDB);
            return true;
        }
        return false;
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public List<Cliente> listarPorNome(String nome){
        return clienteRepository.findByNomeIgnoreCaseContaining(nome);
    }

    public List<Cliente> listarPoNomeQuery(String nome){
        return clienteRepository.encontrarPorNome(nome);
    }

    public boolean existeClienteComNome(String nome){
        return clienteRepository.existsByNome(nome);
    }

    
    public void deletarPorNome(String nome){
        clienteRepository.deleteByNome(nome);
    }

    public void obterPedidos(Integer id){
        Optional<Cliente> clienteById = clienteRepository.findById(id);
        if(clienteById.isPresent()){
            Cliente cliente = clienteRepository.findClienteFetchPedidos(clienteById.get().getId());
            System.out.println(cliente.getPedidos());
            return;
        }
        System.out.println("NOT FOUND");
    }

    public Optional<Cliente> obterClientePorId(Integer id){
        return clienteRepository.findById(id);
    }

    public boolean clienteExistePorId(Integer id){
        return clienteRepository.existsById(id);
    }

    public List<Cliente> obterClienteFiltro(Cliente cliente){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(StringMatcher.CONTAINING);
        Example<Cliente> example = Example.of(cliente , matcher);
        return clienteRepository.findAll(example);

    }
}
