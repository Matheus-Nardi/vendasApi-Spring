package com.mafn.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mafn.dto.PedidoDTO;
import com.mafn.dto.PedidoResponseDTO;
import com.mafn.models.Pedido;
import com.mafn.service.PedidoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<String> salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
        log.info("Requisição do tipo POST para baseURL/pedidos");
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return ResponseEntity.status(CREATED)
                .body(String.format("Pedido criado com sucesso , seu código é: %d", pedido.getId()));

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoResponseDTO> obterPedido(@PathVariable Integer id) {
        log.info("Requisição do tipo GET para baseURL/pedidos/{}", id);
        PedidoResponseDTO responseDTO = pedidoService.obterDetalhesPedido(id);
        return ResponseEntity.status(OK).body(responseDTO);
    }

}
