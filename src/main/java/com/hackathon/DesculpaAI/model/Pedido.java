package com.hackathon.DesculpaAI.model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoAlibi tipoAlibi;
    private int nivelAbsurdo;
    @Enumerated(EnumType.STRING)
    private  StatusPedido status;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime createdAt;


    
    
}
