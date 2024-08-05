package com.mafn.dto;

import java.math.BigDecimal;
import java.util.Set;

import com.mafn.models.ItemPedido;

import lombok.Data;

/**
 * {
 * "cliente": 1,
 * "total" : 200,
 * "itens" :
 *      [
 *          {
 *             "produto" : 1,
 *             "quantidade" : 2
 *          }
 *      ]
 * }
 */
@Data
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private Set<ItemPedidoDTO> itens;

}
