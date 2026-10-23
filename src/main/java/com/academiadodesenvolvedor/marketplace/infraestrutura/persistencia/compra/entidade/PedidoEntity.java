package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoEntity {

    @Id
    private UUID id;

    @Column(name = "vendedor_id",nullable = false)
    private UUID vendedorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private static VendedorEntity vendedor;

    @Column(name = "cliente_id",nullable = false)
    private UUID clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id", nullable = false)
    private static CompraEntity compra;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoPedidoEntity> produtos = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal total;

    public static PedidoEntity fromDomain(Pedido pedido){
        List<ProdutoPedidoEntity> produtoPedidos = pedido.getProdutos()
                .stream()
                .map(ProdutoPedidoEntity::fromDomain).toList();
        return new PedidoEntity(
                pedido.getId(),
                pedido.getVendedorID(),
                pedido.getCompraId(),
                produtoPedidos,
                pedido.getTotal()
        );
    }
}
