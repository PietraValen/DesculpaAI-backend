package com.hackathon.DesculpaAI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.DesculpaAI.dto.RoteiroResponseDTO;
import com.hackathon.DesculpaAI.service.RoteiroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller para gerenciar operações de Roteiros de Alibis. Expõe endpoints
 * para gerar e recuperar roteiros de pedidos de alibis.
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Roteiros", description = "API de gerenciamento de roteiros de alibis")
@Slf4j
public class RoteiroController {

    private final RoteiroService roteiroService;

    public RoteiroController(RoteiroService roteiroService) {
        this.roteiroService = roteiroService;
    }

    /**
     * Obtém ou gera o roteiro para um pedido específico. O pedido deve estar
     * com status ACEITO para que o roteiro seja gerado.
     *
     * @param pedidoId ID do pedido
     * @return ResponseEntity contendo o RoteiroResponseDTO
     */
    @GetMapping("/{pedidoId}/roteiro")
    @Operation(
            summary = "Obter roteiro de um pedido",
            description = "Obtém o roteiro/script do álibi para um pedido. "
            + "O pedido deve estar com status ACEITO."
    )
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Roteiro obtido/gerado com sucesso",
                content = @Content(schema = @Schema(implementation = RoteiroResponseDTO.class))
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Pedido não encontrado"
        ),
        @ApiResponse(
                responseCode = "422",
                description = "Pedido não está ACEITO - não é possível gerar roteiro"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Erro interno do servidor"
        )
    })
    public ResponseEntity<RoteiroResponseDTO> obterRoteiro(
            @Parameter(description = "ID do pedido", example = "1", required = true)
            @PathVariable Long pedidoId) {
        log.info("Recebendo requisição para obter roteiro do pedido ID: {}", pedidoId);

        RoteiroResponseDTO roteiro = roteiroService.obterOuGerarRoteiro(pedidoId);

        log.info("Roteiro obtido/gerado com sucesso para pedido ID: {}", pedidoId);
        return ResponseEntity.status(HttpStatus.OK).body(roteiro);
    }
}
