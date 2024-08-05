package com.mafn.service;

import com.mafn.dto.PedidoDTO;
import com.mafn.models.Pedido;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDto);
}
