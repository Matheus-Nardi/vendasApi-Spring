package com.mafn.dto;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(String descricaoProduto , BigDecimal precoUnitario , Integer quantidade) {

}
