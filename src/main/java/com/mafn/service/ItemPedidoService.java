package com.mafn.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mafn.dto.ItemPedidoDTO;
import com.mafn.dto.ItemPedidoResponseDTO;
import com.mafn.exception.RegraNegocioException;
import com.mafn.models.ItemPedido;
import com.mafn.models.Pedido;
import com.mafn.models.Produto;
import com.mafn.repository.ItemPedidoRepository;
import com.mafn.service.impl.ProdutoServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemPedidoService {

    private final ProdutoServiceImpl produtoService;

    private final ItemPedidoRepository itemPedidoRepository;

    public Set<ItemPedido> itemDTOtoItemPedido(Pedido pedido, Set<ItemPedidoDTO> itensDTO) {

        if (itensDTO.isEmpty()) {
            throw new RegraNegocioException("Um pedido deve possuir pelo menos um item.");
        }
        return itensDTO.stream()
                .map(dto -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(obterProdutoPorId(dto.getProduto()));
                    itemPedido.setQuantidade(dto.getQuantidade());
                    return itemPedido;
                }).collect(Collectors.toSet());

    }

    public void salvarTodos(Set<ItemPedido> itensPedidos) {
        itemPedidoRepository.saveAll(itensPedidos);
    }

    private Produto obterProdutoPorId(Integer id) {
        return produtoService.findById(id);

    }

    public Set<ItemPedidoResponseDTO> toItemResponseDTO(Set<ItemPedido> itemPedidos) {
        if (CollectionUtils.isEmpty(itemPedidos)) {
            return Collections.emptySet();
        }

        return itemPedidos.stream()
                .map(item -> new ItemPedidoResponseDTO(item.getProduto().getDescricao(), item.getProduto().getPreco(),
                        item.getQuantidade()))
                .collect(Collectors.toSet());
    }

    public BigDecimal calcularTotal(Set<ItemPedido> itens) {
        BigDecimal total = new BigDecimal(0);
        for (ItemPedido itemPedido : itens) {
            Produto produto = itemPedido.getProduto();
            BigDecimal preco = produto.getPreco();
            BigDecimal quantidade = BigDecimal.valueOf(itemPedido.getQuantidade());
            total = total.add(preco.multiply(quantidade));
        }
        return total;
    }
}
