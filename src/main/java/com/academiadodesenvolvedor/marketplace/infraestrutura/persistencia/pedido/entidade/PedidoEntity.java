package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.pedido.enums.PedidoStatus;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pedidos")
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    public VendedorEntity vendedor;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    public ClienteEntity cliente;

    @Embedded
    public EnderecoEntity endereco;

    @Column(nullable = false)
    public BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public PedidoStatus status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProdutoPedidoEntity> produtos = new ArrayList<>();
}
