package com.mafn.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.mafn.models.Produto;
import com.mafn.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping(value = "/{id}")
    public Produto obterProdutoPorId(@PathVariable Integer id) {
        return produtoService
                .obterProdutoPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer id) {
        produtoService
                .obterProdutoPorId(id)
                .ifPresentOrElse(produto -> produtoService.deletarProduto(produto),
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoAtualizado) {
        produtoService.atualizarProduto(id, produtoAtualizado);
    }

    @GetMapping
    public List<Produto> obterProdutosPorFiltro(Produto produtoFiltro){
        return produtoService.obterProdutoPorFiltro(produtoFiltro);
    }

}
