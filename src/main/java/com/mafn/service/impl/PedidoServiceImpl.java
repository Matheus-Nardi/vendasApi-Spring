package com.mafn.service.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mafn.dto.PedidoDTO;
import com.mafn.exception.NotFoundException;
import com.mafn.exception.RegraNegocioException;
import com.mafn.models.Cliente;
import com.mafn.models.ItemPedido;
import com.mafn.models.Pedido;
import com.mafn.repository.PedidoRepository;
import com.mafn.service.ClienteService;
import com.mafn.service.ItemPedidoService;
import com.mafn.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteService clienteService;

    private final ItemPedidoService itemPedidoService;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDto) {
        Pedido pedido = new Pedido();
        Cliente cliente = setPedidoCliente(pedidoDto, pedido);
        Set<ItemPedido> itensPedidos =  itemPedidoService.itemDTOtoItemPedido(pedido, pedidoDto.getItens());

        pedido.setTotal(pedidoDto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        
        pedidoRepository.save(pedido);
        itemPedidoService.salvarTodos(itensPedidos);
        pedido.setItens(itensPedidos);

        return pedido;
    }

    private Cliente setPedidoCliente(PedidoDTO pedidoDto, Pedido pedido) {
           Integer idCliente = pedidoDto.getCliente();
           Optional<Cliente> clienteOptional = clienteService.obterClientePorId(idCliente);
           if(clienteOptional.isPresent()){
                return clienteOptional.get();
           }
           throw new NotFoundException(String.format("O cliente de id %d não foi encontrado.", idCliente));
    }

}
