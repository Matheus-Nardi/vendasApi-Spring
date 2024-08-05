package com.mafn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafn.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

}
