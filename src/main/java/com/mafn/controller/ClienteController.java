package com.mafn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mafn.models.Cliente;
import com.mafn.service.ClienteService;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> obterClientePorId(@PathVariable() Integer id) {
        Optional<Cliente>clienteFromDb = clienteService.obterClientePorId(id);
        if(clienteFromDb.isPresent()){
            return ResponseEntity.ok(clienteFromDb.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente){
        Cliente clienteToSave = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteToSave);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarCliente(@PathVariable Integer id){
       if(clienteService.clienteExistePorId(id)){
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
       }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("O cliente de id %d não existe" , id));
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        if(clienteService.atualizar(clienteAtualizado, id)){
            return ResponseEntity.ok(clienteAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterClienteFiltro(Cliente clienteFiltro){
        List<Cliente> clienteExample = clienteService.obterClienteFiltro(clienteFiltro);
        return ResponseEntity.ok(clienteExample);
    }
}
