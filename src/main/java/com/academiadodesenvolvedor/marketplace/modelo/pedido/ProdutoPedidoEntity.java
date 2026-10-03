package com.academiadodesenvolvedor.marketplace.modelo.pedido;

import com.academiadodesenvolvedor.marketplace.modelo.produto.ProdutoEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido_produtos")
public class ProdutoPedidoEntity {

    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    ProdutoEntity produto;

    @Column(nullable = false)
    Integer quantidade;

    @Column(nullable = false)
    BigDecimal precoUnitario;

    @Column(nullable = false)
    BigDecimal valorTotal;
}
