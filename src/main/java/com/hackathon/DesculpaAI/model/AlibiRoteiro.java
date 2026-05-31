package com.hackathon.DesculpaAI.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um roteiro de álibi com as instruções necessárias.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlibiRoteiro {

    @NotBlank(message = "Papel não pode estar vazio")
    private String papel;

    @NotBlank(message = "Missão não pode estar vazia")
    private String missao;

    @NotBlank(message = "Frase obrigatória não pode estar vazia")
    private String fraseObrigatoria;

    @NotBlank(message = "Frase proibida não pode estar vazia")
    private String fraseProibida;
}
