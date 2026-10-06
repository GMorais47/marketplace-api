package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoEntityRepository extends JpaRepository<ProdutoEntity, UUID> {
    List<ProdutoEntity> findByVendedorID(UUID vendedorID);
    List<ProdutoEntity> findByCategoriaID(UUID categoriaID);
}
