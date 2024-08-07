package com.mafn.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafn.models.Cliente;
import com.mafn.service.impl.ClienteServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

   
    private ClienteServiceImpl clienteService;
    

    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findClienteById(@PathVariable() Integer id) {
        log.info("Requisão do tipo GET para baseURL/clientes/{}", id);
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.status(OK).body(cliente);
    }

    @PostMapping

    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        log.info("Requisão do tipo POST para baseURL/clientes");
        clienteService.save(cliente);
        return ResponseEntity.status(CREATED).body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Integer id) {
        log.info("Requisão do tipo DELETE para baseURL/clientes/{}", id);
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        log.info("Requisão do tipo PUT para baseURL/clientes/{}", id);
        clienteService.update(id, clienteAtualizado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findClienteByFilters(Cliente clienteFiltro) {
        log.info("Requisão do tipo GET com query paramters para baseURL/clientes");
        List<Cliente> clientes =clienteService.findByFilter(clienteFiltro);
        return ResponseEntity.status(OK).body(clientes);
    }
}
