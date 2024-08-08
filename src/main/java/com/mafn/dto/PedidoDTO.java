package com.mafn.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 * "cliente": 1,
 * "total" : 200,
 * "itens" :
 * [
 * {
 * "produto" : 1,
 * "quantidade" : 2
 * }
 * ]
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer cliente;
    private Set<ItemPedidoDTO> itens;


    
}
