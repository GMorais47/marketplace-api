package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, UUID> {
    Optional<ClienteEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
