package com.mafn.repository;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mafn.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    List<Cliente> findByNomeIgnoreCaseContaining(String nome);

    boolean existsByNome(String nome);

    boolean existsById(Integer id);

    @Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    void deleteByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id")Integer id);
}
