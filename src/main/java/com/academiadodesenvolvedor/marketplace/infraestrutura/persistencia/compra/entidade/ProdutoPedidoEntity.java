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

    @Column(name = "pedido_id", nullable = false)
    private  UUID pedidoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private static PedidoEntity pedido;

    @Column(name = "produto_id", nullable = false)
    private  UUID produtoId;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private static ProdutoEntity produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal total;

    public static ProdutoPedidoEntity fromDomain(ProdutoPedido pedido) {
        return new ProdutoPedidoEntity(
                pedido.getId(),
                pedido.getPedidoID(),
                pedido.getProdutoID(),
                pedido.getQuantidade(),
                pedido.getPrecoUnitario(),
                pedido.getTotal()
        );
    }
}
