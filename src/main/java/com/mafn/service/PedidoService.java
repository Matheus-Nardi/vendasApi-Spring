package com.mafn.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mafn.models.Cliente;
import com.mafn.models.Pedido;
import com.mafn.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void salvar(Pedido pedido) {
        try {
            pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Pedido> obterPedidosPorCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }
}
