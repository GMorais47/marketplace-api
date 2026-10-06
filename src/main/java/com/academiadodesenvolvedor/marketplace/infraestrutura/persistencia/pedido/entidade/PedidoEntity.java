package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@NoArgsConstructor
public class PedidoEntity {

    @Id
    UUID id;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    VendedorEntity vendedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    ClienteEntity cliente;

    @Embedded
    EnderecoEntity endereco;

    @Column(nullable = false)
    BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProdutoPedidoEntity> produtos = new ArrayList<>();
}
