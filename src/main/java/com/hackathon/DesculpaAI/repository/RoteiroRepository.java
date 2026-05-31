package com.hackathon.DesculpaAI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.DesculpaAI.model.Roteiro;

/**
 * Repository para acesso a dados da entidade Roteiro.
 */
@Repository
public interface RoteiroRepository extends JpaRepository<Roteiro, Long> {

    /**
     * Busca um roteiro pelo ID do pedido.
     *
     * @param pedidoId ID do pedido
     * @return Optional contendo o roteiro, se existir
     */
    Optional<Roteiro> findByPedidoId(Long pedidoId);
}
