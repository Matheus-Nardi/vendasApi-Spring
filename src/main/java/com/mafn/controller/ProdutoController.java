package com.mafn.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafn.models.Produto;
import com.mafn.service.impl.ProdutoServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private ProdutoServiceImpl produtoService;

    public ProdutoController(ProdutoServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody @Valid Produto produto , Authentication authentication) {
        log.info("Requisição do tipo POST para baseURL/produtos feita por {}" , authentication.getName());
        produtoService.save(produto);
        return ResponseEntity.status(CREATED).body(produto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Integer id) {
        log.info("Requisão do tipo GET para baseURL/produtos/{}", id);
        Produto produto = produtoService.findById(id);
        return ResponseEntity.status(OK).body(produto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable Integer id, Authentication authentication) {
        log.info("Requisição do tipo DELETE para baseURL/produtos/{} feita por {}", id , authentication.getName());
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody @Valid Produto produtoAtualizado , Authentication authentication) {
        log.info("Requisição do tipo PUT para baseURL/produtos/{} feita por {}", id , authentication);
        produtoService.update(id, produtoAtualizado);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> obterProdutosPorFiltro(Produto produtoFiltro) {
        log.info("Requisição do tipo GET com query paramters para baseURL/produtos");
        List<Produto> produtos = produtoService.findByFilter(produtoFiltro);
        return ResponseEntity.status(OK).body(produtos);
    }

}
