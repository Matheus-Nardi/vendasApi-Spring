package com.mafn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mafn.exception.NotFoundException;
import com.mafn.models.Produto;
import com.mafn.repository.ProdutoRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProdutoServiceImpl implements SimpleCrud<Produto> {

    private ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public void save(Produto entity) {
        log.info("Salvando um novo produto.");
        produtoRepository.save(entity);
    }

    @Override
    public Produto findById(Integer id) {
        log.info("Buscando por um produto de id {}", id);
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar um produto com id: " + id));
    }

    @Override
    public List<Produto> findAll() {
        log.info("Retornando todos os produtos.");
        return produtoRepository.findAll();
    }

    @Override
    public List<Produto> findByFilter(Produto entityFilter) {
        log.info("Retornando produtos filtrados.");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<Produto> produtoExample = Example.of(entityFilter, exampleMatcher);
        return produtoRepository.findAll(produtoExample);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        log.info("Deletando produto de id {}", id);
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar um produto com id: " + id));
        produtoRepository.delete(produto);
        log.info("Produto de id {} deletado com sucesso", id);
    }

    @Override
    @Transactional
    public void update(Integer id, Produto entityToUpdate) {
        log.info("Atualizando produto de id {}", id);
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty())
            throw new NotFoundException("Não foi possível encontrar um produto com id: " + id);
        Produto produtoFromDb = produtoOptional.get();
        log.info("Produto de id {} encontrado", id);

        if (entityToUpdate.getDescricao() != null) {
            produtoFromDb.setDescricao(entityToUpdate.getDescricao());
        }
        if (entityToUpdate.getPreco() != null) {
            produtoFromDb.setPreco(entityToUpdate.getPreco());
        }

        produtoRepository.save(produtoFromDb);
        log.info("Produto de id {} atualizado com sucesso", id);
    }

}
