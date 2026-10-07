package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.repositorio;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoEntityRepository extends JpaRepository<PedidoEntity, UUID> {
    List<PedidoEntity> findByVendedorID(UUID vendedorID);
    List<PedidoEntity> findByClienteID(UUID clienteID);
}
