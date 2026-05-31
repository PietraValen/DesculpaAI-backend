package com.hackathon.DesculpaAI.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para resposta com o roteiro do álibi. Contém as instruções detalhadas
 * para executar o álibi gerado.
 */
@Schema(name = "RoteiroResponseDTO", description = "Roteiro/script do álibi gerado para o pedido")
public record RoteiroResponseDTO(
        @Schema(description = "ID único do roteiro", example = "1")
        Long id,
        @Schema(description = "Papel que o usuário deve desempenhar", example = "Executivo importante em reunião")
        String papel,
        @Schema(description = "Missão/objetivo principal do álibi", example = "Convencer que estava em reunião importante")
        String missao,
        @Schema(description = "Frase que DEVE ser dita em algum momento", example = "Desculpe, estava em uma reunião crítica")
        String fraseObrigatoria,
        @Schema(description = "Frase que NÃO PODE ser dita (evitar a todo custo)", example = "Eu estava na praia")
        String fraseProibida,
        @Schema(description = "ID do pedido associado", example = "1")
        Long pedidoId
        ) {

}
