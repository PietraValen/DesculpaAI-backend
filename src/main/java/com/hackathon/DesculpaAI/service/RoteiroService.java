package com.hackathon.DesculpaAI.service;

import com.hackathon.DesculpaAI.Data.BancoAlibi;
import com.hackathon.DesculpaAI.dto.RoteiroResponseDTO;
import com.hackathon.DesculpaAI.exception.ResourceNotFoundException;
import com.hackathon.DesculpaAI.model.AlibiRoteiro;
import com.hackathon.DesculpaAI.model.Pedido;
import com.hackathon.DesculpaAI.model.Roteiro;
import com.hackathon.DesculpaAI.model.StatusPedido;
import com.hackathon.DesculpaAI.repository.PedidoRepository;
import com.hackathon.DesculpaAI.repository.RoteiroRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Serviço de negócio para gerenciar roteiros de alibis. Responsável por gerar,
 * validar e recuperar roteiros dos pedidos.
 */
@Service
@Transactional
@Slf4j
public class RoteiroService {

    private final RoteiroRepository roteiroRepository;
    private final PedidoRepository pedidoRepository;
    private final BancoAlibi bancoAlibi;

    public RoteiroService(
            RoteiroRepository roteiroRepository,
            PedidoRepository pedidoRepository,
            BancoAlibi bancoAlibi) {
        this.roteiroRepository = roteiroRepository;
        this.pedidoRepository = pedidoRepository;
        this.bancoAlibi = bancoAlibi;
    }

    /**
     * Obtém ou gera um roteiro para um pedido.
     *
     * @param pedidoId ID do pedido
     * @return RoteiroResponseDTO com os dados do roteiro
     * @throws ResourceNotFoundException se o pedido não existir
     * @throws ResponseStatusException se o pedido não estiver ACEITO
     */
    public RoteiroResponseDTO obterOuGerarRoteiro(Long pedidoId) {
        log.info("Buscando ou gerando roteiro para pedido ID: {}", pedidoId);

        // Busca o pedido
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> {
                    log.warn("Pedido não encontrado: {}", pedidoId);
                    return new ResourceNotFoundException("Pedido com ID " + pedidoId + " não encontrado");
                });

        // [C5] Valida se o status é ACEITO
        if (pedido.getStatus() != StatusPedido.ACEITO) {
            log.warn("Tentativa de acessar roteiro de pedido não ACEITO. Pedido ID: {}, Status: {}",
                    pedidoId, pedido.getStatus());
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "Pedido precisa estar ACEITO para gerar roteiro"
            );
        }

        // Verifica se já existe um roteiro para este pedido
        return roteiroRepository.findByPedidoId(pedidoId)
                .map(this::converterParaDTO)
                .orElseGet(() -> gerarNovoRoteiro(pedido));
    }

    /**
     * Gera um novo roteiro para um pedido.
     *
     * @param pedido o pedido para o qual gerar o roteiro
     * @return RoteiroResponseDTO do roteiro gerado
     */
    private RoteiroResponseDTO gerarNovoRoteiro(Pedido pedido) {
        log.info("Gerando novo roteiro para pedido ID: {}", pedido.getId());

        // Gera o alibi roteiro a partir do BancoAlibi
        AlibiRoteiro alibiRoteiro = bancoAlibi.obterAlibiAleatorio(
                pedido.getTipoAlibi(),
                pedido.getNivelAbsurdo()
        );

        // Cria a entidade Roteiro
        Roteiro roteiro = Roteiro.builder()
                .pedido(pedido)
                .papel(alibiRoteiro.getPapel())
                .missao(alibiRoteiro.getMissao())
                .fraseObrigatoria(alibiRoteiro.getFraseObrigatoria())
                .fraseProibida(alibiRoteiro.getFraseProibida())
                .build();

        // Salva no banco de dados
        Roteiro roteirSalvo = roteiroRepository.save(roteiro);
        log.info("Roteiro gerado e salvo com sucesso. ID: {}", roteirSalvo.getId());

        return converterParaDTO(roteirSalvo);
    }

    /**
     * Converte uma entidade Roteiro para RoteiroResponseDTO.
     *
     * @param roteiro entidade Roteiro
     * @return RoteiroResponseDTO
     */
    private RoteiroResponseDTO converterParaDTO(Roteiro roteiro) {
        return new RoteiroResponseDTO(
                roteiro.getId(),
                roteiro.getPapel(),
                roteiro.getMissao(),
                roteiro.getFraseObrigatoria(),
                roteiro.getFraseProibida(),
                roteiro.getPedido().getId()
        );
    }
}
