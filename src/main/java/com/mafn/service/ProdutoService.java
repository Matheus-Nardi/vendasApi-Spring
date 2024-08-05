package com.mafn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.mafn.models.Produto;
import com.mafn.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> obterProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public void deletarProduto(Produto produto) {
        produtoRepository.delete(produto);
    }

    public void atualizarProduto(Integer id, Produto produtoAtualizado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isPresent()) {
            Produto produtoDB = produtoOptional.get();
            if (produtoAtualizado.getPreco() != null) {
                produtoDB.setPreco(produtoAtualizado.getPreco());
            }

            if (produtoAtualizado.getDescricao() != null) {
                produtoDB.setDescricao(produtoAtualizado.getDescricao());
            }

            produtoRepository.save(produtoDB);
        }
    }


    public List<Produto> obterProdutoPorFiltro(Produto produtoFiltro){
        ExampleMatcher exampleMatcherProduto = ExampleMatcher
                                                .matching()
                                                .withIgnoreCase()
                                                .withStringMatcher(StringMatcher.CONTAINING);
        
        Example<Produto> exampleProduto = Example.of(produtoFiltro , exampleMatcherProduto);
        return produtoRepository.findAll(exampleProduto);
    }

}
