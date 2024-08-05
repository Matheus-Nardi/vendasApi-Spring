package com.mafn.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mafn.dto.PedidoDTO;
import com.mafn.models.Pedido;
import com.mafn.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido =  pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    
}
