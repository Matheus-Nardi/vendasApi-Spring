package com.mafn.service.impl;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mafn.dto.ItemPedidoDTO;
import com.mafn.dto.PedidoDTO;
import com.mafn.exception.RegraNegocioException;
import com.mafn.models.ItemPedido;
import com.mafn.models.Pedido;
import com.mafn.repository.ItemPedidoRepository;
import com.mafn.repository.PedidoRepository;
import com.mafn.service.ClienteService;
import com.mafn.service.PedidoService;
import com.mafn.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteService clienteService;

    private final ProdutoService produtoService;

    private final ItemPedidoRepository itemPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDto.getTotal());
        pedido.setDataPedido(LocalDate.now());

        Integer idCliente = pedidoDto.getCliente();
        clienteService.obterClientePorId(idCliente)
                .ifPresentOrElse(cliente -> pedido.setCliente(cliente),
                        () -> new RegraNegocioException("O id do cliente não existe"));

        Set<ItemPedido> itensPedidos =  converterItensPedido(pedido, pedidoDto.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);

        return pedido;
    }

    private Set<ItemPedido> converterItensPedido(Pedido pedido, Set<ItemPedidoDTO> itensDTO) {
        if (itensDTO.isEmpty()) {
            throw new RegraNegocioException("Um pedido deve possuir pelo menos um item.");
        }

        return itensDTO.stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    produtoService.obterProdutoPorId(idProduto)
                            .ifPresentOrElse(produto -> itemPedido.setProduto(produto),
                                    () -> new RegraNegocioException("O id do produto não existe"));
                    return itemPedido;
                })
                .collect(Collectors.toSet());
    }
}
