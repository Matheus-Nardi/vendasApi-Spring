package com.mafn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mafn.models.Cliente;
import com.mafn.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public Cliente obterClientePorId(@PathVariable() Integer id) {
        return clienteService
                .obterClientePorId(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND , "Cliente não encontrado"));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@RequestBody Cliente cliente){
       return clienteService.salvar(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id){
        clienteService
        .obterClientePorId(id)
        .ifPresentOrElse(
            cliente -> clienteService.deletar(cliente),
            () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"); }
        );
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        boolean atualizado = clienteService.atualizar(clienteAtualizado, id);
        if (atualizado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping
    public List<Cliente> obterClienteFiltro(Cliente clienteFiltro){
       return  clienteService.obterClienteFiltro(clienteFiltro);
        
    }
}
