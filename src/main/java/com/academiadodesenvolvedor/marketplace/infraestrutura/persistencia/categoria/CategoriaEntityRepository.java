package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoriaEntityRepository extends JpaRepository<CategoriaEntity, UUID> {
    boolean existsByPath(String path);
    List<CategoriaEntity> findByIsDestaque(boolean isDestaque);
}
