package com.mafn.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mafn.dto.ItemPedidoResponseDTO;
import com.mafn.dto.PedidoDTO;
import com.mafn.dto.PedidoResponseDTO;
import com.mafn.exception.NotFoundException;
import com.mafn.exception.RegraNegocioException;
import com.mafn.models.Cliente;
import com.mafn.models.ItemPedido;
import com.mafn.models.Pedido;
import com.mafn.models.enums.StatusPedido;
import com.mafn.repository.PedidoRepository;
import com.mafn.service.ItemPedidoService;
import com.mafn.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteServiceImpl clienteService;

    private final ItemPedidoService itemPedidoService;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        Cliente cliente = setPedidoCliente(pedidoDto, pedido);
        Set<ItemPedido> itensPedidos = itemPedidoService.itemDTOtoItemPedido(pedido, pedidoDto.getItens());

        pedido.setTotal(itemPedidoService.calcularTotal(itensPedidos));
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        pedidoRepository.save(pedido);
        itemPedidoService.salvarTodos(itensPedidos);
        pedido.setItens(itensPedidos);

        return pedido;
    }

    private Cliente setPedidoCliente(PedidoDTO pedidoDto, Pedido pedido) {
        Integer idCliente = pedidoDto.getCliente();
        return clienteService.findById(idCliente);
    }

    @Override
    public PedidoResponseDTO obterDetalhesPedido(Integer id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findByIdFetchItens(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            Cliente cliente = pedido.getCliente();
            Set<ItemPedidoResponseDTO> itens = itemPedidoService.toItemResponseDTO(pedido.getItens());
            return new PedidoResponseDTO(id, cliente.getCpf(), cliente.getNome(), pedido.getTotal(),
                    pedido.getDataPedido(), pedido.getStatus(), itens);
        }

        throw new NotFoundException(String.format("O pedido de id %d não foi encontrado", id));

    }

    @Override
    @Transactional
    public void cancelarPedido(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("O pedido de id %d não foi encontrado", id)));
        if(pedido.getStatus().equals(StatusPedido.CANCELADO)){
            throw new RegraNegocioException("O pedido já foi cancelado.");
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
}
