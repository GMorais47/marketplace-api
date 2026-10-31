package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.compra.ProdutoPedido;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido_produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoPedidoEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private BigDecimal total;

    public static ProdutoPedidoEntity fromDomain(ProdutoPedido prd, PedidoEntity pedido, ProdutoEntity produto) {
        return new ProdutoPedidoEntity(
                prd.getId(),
                pedido,
                produto,
                prd.getQuantidade(),
                prd.getValor(),
                prd.getTotal()
        );
    }
}
