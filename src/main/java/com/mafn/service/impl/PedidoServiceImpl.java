package com.mafn.service.impl;

import org.springframework.stereotype.Service;

import com.mafn.repository.PedidoRepository;
import com.mafn.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    

}
