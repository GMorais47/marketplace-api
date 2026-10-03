package com.academiadodesenvolvedor.marketplace.modelo.produto;

import com.academiadodesenvolvedor.marketplace.modelo.categoria.CategoriaEntity;
import com.academiadodesenvolvedor.marketplace.modelo.usuario.VendedorEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
public class ProdutoEntity {

    @Id
    UUID id;

    @ManyToOne
            @JoinColumn(name = "vendedor_id", nullable = false)
    VendedorEntity vendedor;

    @Column(nullable = false, length = 150)
    String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    CategoriaEntity categoria;

    @Column(columnDefinition = "TEXT", nullable = false)
    String descricao;

    @Column(columnDefinition = "TEXT")
    String foto;

    @Column(nullable = false)
    BigDecimal preco;

    @Column(nullable = false)
    Float avaliacao;

}
