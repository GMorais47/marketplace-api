package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.compra.Compra;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_compras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompraEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    public ClienteEntity cliente;

    @Embedded
    private EnderecoEntity endereco;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoEntity> pedidos;

    @Column(nullable = false)
    private BigDecimal total;

    @Column()
    private Integer parcelas;

    @Column()
    private String pagamentoId;

    @Column()
    private String status;

    public static CompraEntity fromDomain(
            Compra compra,
            ClienteEntity clienteEntity,
            List<PedidoEntity> pedidos
    ) {
        EnderecoEntity enderecoEntity = EnderecoEntity.fromDomain(compra.getEndereco());

        return new CompraEntity(
                compra.getId(),
                clienteEntity,
                enderecoEntity,
                pedidos,
                compra.getTotal(),
                compra.getParcelas(),
                compra.getPagamentoId(),
                compra.getStatus()
        );
    }

}
