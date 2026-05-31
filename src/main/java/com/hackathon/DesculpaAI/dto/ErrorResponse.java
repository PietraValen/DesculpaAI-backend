package com.hackathon.DesculpaAI.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de erro.
 */
@Schema(name = "ErrorResponse", description = "Resposta de erro padronizada")
public record ErrorResponse(
        @Schema(description = "Mensagem de erro", example = "Recurso não encontrado")
        String mensagem,
        @Schema(description = "Timestamp do erro")
        LocalDateTime timestamp,
        @Schema(description = "Status HTTP", example = "404")
        int status
        ) {

    public ErrorResponse(String mensagem, LocalDateTime timestamp) {
        this(mensagem, timestamp, 0);
    }
}
