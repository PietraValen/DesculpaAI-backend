package com.hackathon.DesculpaAI.model;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricaoSituacao;
    @Enumerated(EnumType.STRING)
    private TipoAlibi tipoAlibi;
    private int nivelAbsurdo;
    @Enumerated(EnumType.STRING)
    private  StatusPedido status;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getdescricaoSituacao(){
        return descricaoSituacao;
    }
    public void setDescricaoSituacao(String descricaoSituacao){
        this.descricaoSituacao = descricaoSituacao;
    }
    public TipoAlibi getTipoAlibi() {
        return tipoAlibi;
    }
    public void setTipoAlibi(TipoAlibi tipoAlibi) {
        this.tipoAlibi = tipoAlibi;
    }
    public int getNivelAbsurdo() {
        return nivelAbsurdo;
    }
    public void setNivelAbsurdo(int nivelAbsurdo) {
        this.nivelAbsurdo = nivelAbsurdo;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    
    
}
