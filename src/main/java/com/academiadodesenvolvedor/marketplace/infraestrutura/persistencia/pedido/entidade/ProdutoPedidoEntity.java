package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido_produtos")
@AllArgsConstructor
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
