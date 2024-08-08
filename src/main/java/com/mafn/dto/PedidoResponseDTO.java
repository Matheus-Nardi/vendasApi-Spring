package com.mafn.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import com.mafn.models.enums.StatusPedido;

public record PedidoResponseDTO(Integer codigo, String cpfCliente, String nomeCliente, BigDecimal total,
        LocalDate dataPedido, StatusPedido status, Set<ItemPedidoResponseDTO> itens) {

}
