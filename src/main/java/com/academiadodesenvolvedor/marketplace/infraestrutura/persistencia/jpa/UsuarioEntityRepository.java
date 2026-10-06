package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.jpa;

import com.academiadodesenvolvedor.marketplace.modelo.usuario.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioEntityRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
