package com.hackathon.DesculpaAI.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.DesculpaAI.dto.CriarPedidoDTO;
import com.hackathon.DesculpaAI.dto.PedidoResponseDTO;
import com.hackathon.DesculpaAI.model.StatusPedido;
import com.hackathon.DesculpaAI.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller para gerenciar operações de Pedidos de Alibis.
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos de alibis")
@Slf4j
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Cria um novo pedido de álibi.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar novo pedido", description = "Cria um novo pedido de álibi")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody CriarPedidoDTO criarPedidoDTO) {
        log.info("Recebendo requisição para criar novo pedido");
        PedidoResponseDTO pedido = pedidoService.criarPedido(criarPedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    /**
     * Busca um pedido por ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obter pedido por ID", description = "Retorna os dados de um pedido específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoResponseDTO> obterPedidoPorId(
            @PathVariable(name = "id")
            @Parameter(description = "ID do pedido a ser buscado") Long id) {
        log.info("Buscando pedido com ID: {}", id);
        PedidoResponseDTO pedido = pedidoService.obterPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    /**
     * Lista todos os pedidos.
     */
    @GetMapping
    @Operation(summary = "Listar todos os pedidos", description = "Retorna uma lista de todos os pedidos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<PedidoResponseDTO>> listarTodosPedidos() {
        log.info("Listando todos os pedidos");
        List<PedidoResponseDTO> pedidos = pedidoService.listarTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Lista pedidos por status.
     */
    @GetMapping("/status/{status}")
    @Operation(summary = "Listar pedidos por status", description = "Retorna lista de pedidos filtrada por status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Status inválido"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorStatus(
            @PathVariable(name = "status")
            @Parameter(description = "Status dos pedidos a buscar", example = "ABERTO") StatusPedido status) {
        log.info("Listando pedidos com status: {}", status);
        List<PedidoResponseDTO> pedidos = pedidoService.listarPedidosPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Atualiza o status de um pedido.
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido", description = "Atualiza o status de um pedido existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Status inválido fornecido"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPedido(
            @PathVariable(name = "id")
            @Parameter(description = "ID do pedido a ser atualizado") Long id,
            @RequestParam(name = "novoStatus")
            @Parameter(description = "Novo status para o pedido", example = "ACEITO") StatusPedido novoStatus) {
        log.info("Atualizando status do pedido ID: {} para: {}", id, novoStatus);
        PedidoResponseDTO pedidoAtualizado = pedidoService.atualizarStatusPedido(id, novoStatus);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    /**
     * Deleta um pedido.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar pedido", description = "Remove um pedido do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarPedido(
            @PathVariable(name = "id")
            @Parameter(description = "ID do pedido a ser deletado") Long id) {
        log.info("Deletando pedido com ID: {}", id);
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtém um álibi para um pedido.
     */
    @GetMapping("/{id}/alibi")
    @Operation(summary = "Obter álibi do pedido", description = "Retorna um álibi gerado para o pedido especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Álibi retornado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> obterAlibParaPedido(
            @PathVariable(name = "id")
            @Parameter(description = "ID do pedido para o qual obter o álibi") Long id) {
        log.info("Obtendo álibi para pedido ID: {}", id);
        String alibi = pedidoService.obterAlibParaPedido(id);
        return ResponseEntity.ok(alibi);
    }
}
