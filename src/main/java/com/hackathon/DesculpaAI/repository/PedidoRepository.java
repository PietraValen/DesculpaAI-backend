package com.hackathon.DesculpaAI.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.DesculpaAI.model.Pedido;
import com.hackathon.DesculpaAI.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status);
}