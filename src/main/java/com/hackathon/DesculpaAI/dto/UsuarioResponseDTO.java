package com.hackathon.DesculpaAI.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta de um usuário.
 */
@Schema(name = "UsuarioResponseDTO", description = "Dados do usuário retornado pela API")
public record UsuarioResponseDTO(
        @Schema(description = "ID único do usuário", example = "1")
        Long id,
        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String nome,
        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email,
        @Schema(description = "Data de criação do usuário")
        LocalDateTime criadoEm,
        @Schema(description = "Data da última atualização")
        LocalDateTime atualizadoEm
        ) {

}
