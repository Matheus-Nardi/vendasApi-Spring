package com.mafn.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mafn.dto.ItemPedidoDTO;
import com.mafn.exception.NotFoundException;
import com.mafn.exception.RegraNegocioException;
import com.mafn.models.ItemPedido;
import com.mafn.models.Pedido;
import com.mafn.models.Produto;
import com.mafn.repository.ItemPedidoRepository;

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
}
