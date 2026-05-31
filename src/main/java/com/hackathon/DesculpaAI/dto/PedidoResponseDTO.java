package com.hackathon.DesculpaAI.dto;

import java.time.LocalDateTime;

import com.hackathon.DesculpaAI.model.StatusPedido;
import com.hackathon.DesculpaAI.model.TipoAlibi;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de um pedido.
 */
@Schema(name = "PedidoResponseDTO", description = "Dados do pedido retornado pela API")
public record PedidoResponseDTO(
        @Schema(description = "ID único do pedido", example = "1")
        Long id,
        @Schema(description = "Tipo do álibi", example = "TRABALHO")
        TipoAlibi tipoAlibi,
        @Schema(description = "Nível de absurdo (1-5)", example = "3")
        int nivelAbsurdo,
        @Schema(description = "Status atual do pedido", example = "ABERTO")
        StatusPedido status,
        @Schema(description = "ID do usuário", example = "1")
        Long usuarioId,
        @Schema(description = "Data de criação do pedido")
        LocalDateTime criadoEm,
        @Schema(description = "Data da última atualização")
        LocalDateTime atualizadoEm
        ) {

}
