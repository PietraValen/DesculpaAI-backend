package com.hackathon.DesculpaAI.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.DesculpaAI.dto.UsuarioDTO;
import com.hackathon.DesculpaAI.dto.UsuarioResponseDTO;
import com.hackathon.DesculpaAI.exception.ResourceNotFoundException;
import com.hackathon.DesculpaAI.model.Usuario;
import com.hackathon.DesculpaAI.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Serviço de negócio para operações com Usuários.
 */
@Service
@Transactional
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cria um novo usuário.
     */
    public UsuarioResponseDTO criarUsuario(UsuarioDTO usuarioDTO) {
        log.info("Criando novo usuário com email: {}", usuarioDTO.email());

        Usuario usuario = Usuario.builder()
                .nome(usuarioDTO.nome())
                .email(usuarioDTO.email())
                .build();

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        log.info("Usuário criado com ID: {}", usuarioSalvo.getId());

        return mapearParaResponseDTO(usuarioSalvo);
    }

    /**
     * Busca um usuário por ID.
     */
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obterUsuarioPorId(Long id) {
        log.info("Buscando usuário com ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        return mapearParaResponseDTO(usuario);
    }

    /**
     * Lista todos os usuários.
     */
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        log.info("Listando todos os usuários");

        return usuarioRepository.findAll().stream()
                .map(this::mapearParaResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Atualiza um usuário existente.
     */
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        log.info("Atualizando usuário com ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        log.info("Usuário atualizado: {}", id);

        return mapearParaResponseDTO(usuarioAtualizado);
    }

    /**
     * Deleta um usuário.
     */
    public void deletarUsuario(Long id) {
        log.info("Deletando usuário com ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        usuarioRepository.delete(usuario);
        log.info("Usuário deletado: {}", id);
    }

    /**
     * Mapeia uma entidade Usuario para UsuarioResponseDTO.
     */
    private UsuarioResponseDTO mapearParaResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm()
        );
    }
}
