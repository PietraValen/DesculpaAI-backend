package com.hackathon.DesculpaAI.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para criação e atualização de usuários.
 */
@Schema(name = "UsuarioDTO", description = "Dados de um usuário")
public record UsuarioDTO(
        @NotBlank(message = "Nome não pode estar vazio")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String nome,
        @NotBlank(message = "Email não pode estar vazio")
        @Email(message = "Email deve ser válido")
        @Schema(description = "Email único do usuário", example = "joao@example.com")
        String email
        ) {

}
