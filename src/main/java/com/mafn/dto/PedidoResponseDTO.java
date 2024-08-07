package com.mafn.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record PedidoResponseDTO(Integer codigo , String cpfCliente, String nomeCliente , BigDecimal total , Set<ItemPedidoResponseDTO> itens ,
              LocalDate dataPedido ) {

}
