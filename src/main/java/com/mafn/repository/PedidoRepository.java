package com.mafn.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mafn.models.Cliente;
import com.mafn.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    Set<Pedido> findByCliente(Cliente cliente);
    
}
