package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, UUID> {}
