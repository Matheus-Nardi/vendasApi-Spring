package com.mafn.dto;

import java.util.Set;

import com.mafn.validation.NotEmptyCollection;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * {
 * "cliente": 1,
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

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotEmptyCollection(message = "{campo.itens-pedido.obrigatorio}")
    private Set<ItemPedidoDTO> itens;

}
