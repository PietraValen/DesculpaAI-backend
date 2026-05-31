package com.hackathon.DesculpaAI.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.DesculpaAI.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
