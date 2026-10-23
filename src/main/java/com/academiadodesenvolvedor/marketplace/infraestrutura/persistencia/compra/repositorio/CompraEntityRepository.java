package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraEntityRepository extends JpaRepository<CompraEntity, UUID> {
}
