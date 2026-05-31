package com.hackathon.DesculpaAI.dto;

import com.hackathon.DesculpaAI.model.TipoAlibi;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para requisição de criação de pedido de álibi. Utilizado para validação
 * de entrada (input) dos endpoints.
 */
@Schema(name = "PedidoRequestDTO", description = "Dados para criar um novo pedido de álibi")
public record PedidoRequestDTO(
        @NotNull(message = "Tipo de álibi é obrigatório")
        @Schema(description = "Tipo do álibi desejado", example = "TRABALHO")
        TipoAlibi tipoAlibi,
        @NotNull(message = "Nível de absurdo é obrigatório")
        @Min(value = 1, message = "Nível de absurdo deve estar entre 1 e 5")
        @Max(value = 5, message = "Nível de absurdo deve estar entre 1 e 5")
        @Schema(description = "Nível de absurdo do álibi (1-5)", example = "3")
        int nivelAbsurdo,
        @NotNull(message = "ID do usuário é obrigatório")
        @Schema(description = "ID do usuário que está fazendo o pedido", example = "1")
        Long usuarioId
        ) {

}
