package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.ProdutoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoPedidoEntityRepository extends JpaRepository<ProdutoPedidoEntity, UUID> {}
