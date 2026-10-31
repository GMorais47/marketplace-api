package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.ProdutoPedido;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", nullable = false)
    private VendedorEntity vendedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id", nullable = false)
    private CompraEntity compra;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoPedidoEntity> produtos;

    @Column(nullable = false)
    private BigDecimal total;

    public static PedidoEntity fromDomain(
            Pedido prd,
            CompraEntity compra,
            VendedorEntity vendedor,
            List<ProdutoPedidoEntity> produtos
    ){

        return new PedidoEntity(
                prd.getId(),
                vendedor,
                compra,
                produtos,
                prd.getTotal()
        );
    }
}
