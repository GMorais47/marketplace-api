package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade.ProdutoPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoPedidoEntityRepository extends JpaRepository<ProdutoPedidoEntity, UUID> {
    List<ProdutoPedidoEntity> findByPedidoID(UUID id);
}
