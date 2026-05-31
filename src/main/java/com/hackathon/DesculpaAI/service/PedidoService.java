package com.hackathon.DesculpaAI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.DesculpaAI.Data.BancoAlibi;
import com.hackathon.DesculpaAI.dto.CriarPedidoDTO;
import com.hackathon.DesculpaAI.dto.PedidoResponseDTO;
import com.hackathon.DesculpaAI.exception.ResourceNotFoundException;
import com.hackathon.DesculpaAI.model.NivelAbsurdo;
import com.hackathon.DesculpaAI.model.Pedido;
import com.hackathon.DesculpaAI.model.StatusPedido;
import com.hackathon.DesculpaAI.model.TipoAlibi;
import com.hackathon.DesculpaAI.model.Usuario;
import com.hackathon.DesculpaAI.repository.PedidoRepository;
import com.hackathon.DesculpaAI.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Serviço de negócio para operações com Pedidos de Alibis.
 */
@ Service 

    @Transactional
    @Slf4j
    public class PedidoService {

        private final PedidoRepository pedidoRepository;
        private final UsuarioRepository usuarioRepository;
        private final BancoAlibi bancoAlibi;

        public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, BancoAlibi bancoAlibi) {
            this.pedidoRepository = pedidoRepository;
            this.usuarioRepository = usuarioRepository;
            this.bancoAlibi = bancoAlibi;
        }

        /**
         * Cria um novo pedido de álibi.
         */
        public PedidoResponseDTO criarPedido(CriarPedidoDTO criarPedidoDTO) {
            log.info("Criando novo pedido - Tipo: {}, Nível: {}, Usuário ID: {}",
                    criarPedidoDTO.tipoAlibi(), criarPedidoDTO.nivelAbsurdo(), criarPedidoDTO.usuarioId());

            Usuario usuario = usuarioRepository.findById(criarPedidoDTO.usuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + criarPedidoDTO.usuarioId()));

            NivelAbsurdo nivel = converterNivelAbsurdo(criarPedidoDTO.nivelAbsurdo());

            Pedido pedido = Pedido.builder()
                    .tipoAlibi(criarPedidoDTO.tipoAlibi())
                    .nivelAbsurdo(criarPedidoDTO.nivelAbsurdo())
                    .status(StatusPedido.ABERTO)
                    .usuario(usuario)
                    .build();

            Pedido pedidoSalvo = pedidoRepository.save(pedido);
            log.info("Pedido criado com ID: {}", pedidoSalvo.getId());

            return mapearParaResponseDTO(pedidoSalvo);
        }

        /**
         * Busca um pedido por ID.
         */
        @Transactional(readOnly = true)
        public PedidoResponseDTO obterPedidoPorId(Long id) {
            log.info("Buscando pedido com ID: {}", id);

            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));

            return mapearParaResponseDTO(pedido);
        }

        /**
         * Lista todos os pedidos.
         */
        @Transactional(readOnly = true)
        public List<PedidoResponseDTO> listarTodosPedidos() {
            log.info("Listando todos os pedidos");

            return pedidoRepository.findAll().stream()
                    .map(this::mapearParaResponseDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Lista pedidos por status.
         */
        @Transactional(readOnly = true)
        public List<PedidoResponseDTO> listarPedidosPorStatus(StatusPedido status) {
            log.info("Listando pedidos com status: {}", status);

            return pedidoRepository.findByStatus(status).stream()
                    .map(this::mapearParaResponseDTO)
                    .collect(Collectors.toList());
        }

        /**
         * Atualiza o status de um pedido.
         */
        public PedidoResponseDTO atualizarStatusPedido(Long id, StatusPedido novoStatus) {
            log.info("Atualizando status do pedido ID: {} para: {}", id, novoStatus);

            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));

            pedido.setStatus(novoStatus);
            Pedido pedidoAtualizado = pedidoRepository.save(pedido);

            log.info("Status do pedido atualizado: {}", id);
            return mapearParaResponseDTO(pedidoAtualizado);
        }

        /**
         * Deleta um pedido.
         */
        public void deletarPedido(Long id) {
            log.info("Deletando pedido com ID: {}", id);

            Pedido pedido = pedidoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + id));

            pedidoRepository.delete(pedido);
            log.info("Pedido deletado: {}", id);
        }

        /**
         * Obtém um álibi para um pedido.
         */
        @Transactional(readOnly = true)
        public String obterAlibParaPedido(Long pedidoId) {
            log.info("Obtendo álibi para pedido ID: {}", pedidoId);

            Pedido pedido = pedidoRepository.findById(pedidoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com ID: " + pedidoId));

            NivelAbsurdo nivel = converterNivelAbsurdo(pedido.getNivelAbsurdo());
            return bancoAlibi.obterAlibEAleatorio(pedido.getTipoAlibi(), nivel);
        }

        /**
         * Converte um inteiro (1-5) em NivelAbsurdo.
         */
        private NivelAbsurdo converterNivelAbsurdo(int nivel) {
            return switch (nivel) {
                case 1 ->
                    NivelAbsurdo.BAIXO;
                case 2 ->
                    NivelAbsurdo.MEDIO;
                case 3 ->
                    NivelAbsurdo.ALTO;
                case 4 ->
                    NivelAbsurdo.CINEMATOGRAFICO;
                case 5 ->
                    NivelAbsurdo.APOCALIPTICO;
                default ->
                    NivelAbsurdo.BAIXO;
            };
        }

        /**
         * Mapeia uma entidade Pedido para PedidoResponseDTO.
         */
        private PedidoResponseDTO mapearParaResponseDTO(Pedido pedido) {
            return new PedidoResponseDTO(
                    pedido.getId(),
                    pedido.getTipoAlibi(),
                    pedido.getNivelAbsurdo(),
                    pedido.getStatus(),
                    pedido.getUsuario().getId(),
                    pedido.getCriadoEm(),
                    pedido.getAtualizadoEm()
            );
        }
    }
    
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
