package com.academiadodesenvolvedor.marketplace.dominio.compra.repositorio;

import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository {
    List<Pedido> findByVendedorID(UUID vendedorID);
    List<Pedido> findByClienteID(UUID clienteID);
    void save(Pedido pedido);
}