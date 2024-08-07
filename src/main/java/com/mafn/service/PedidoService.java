package com.mafn.service;

import com.mafn.dto.PedidoDTO;
import com.mafn.dto.PedidoResponseDTO;
import com.mafn.models.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDto);

    PedidoResponseDTO obterDetalhesPedido(Integer id);
}
