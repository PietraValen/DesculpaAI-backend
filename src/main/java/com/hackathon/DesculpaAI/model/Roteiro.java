package com.hackathon.DesculpaAI.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entidade que representa um roteiro de álibi gerado para um pedido. Armazena
 * as instruções específicas para executar o álibi.
 */
@Entity
@Table(name = "roteiros")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "pedido")
public class Roteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pedido é obrigatório")
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_roteiro_pedido"))
    private Pedido pedido;

    @NotBlank(message = "Papel não pode estar vazio")
    private String papel;

    @NotBlank(message = "Missão não pode estar vazia")
    private String missao;

    @NotBlank(message = "Frase obrigatória não pode estar vazia")
    private String fraseObrigatoria;

    @NotBlank(message = "Frase proibida não pode estar vazia")
    private String fraseProibida;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
