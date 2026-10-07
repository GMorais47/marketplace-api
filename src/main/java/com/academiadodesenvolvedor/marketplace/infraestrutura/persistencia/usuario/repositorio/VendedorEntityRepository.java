package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendedorEntityRepository extends JpaRepository<VendedorEntity, UUID> {
    Optional<VendedorEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
