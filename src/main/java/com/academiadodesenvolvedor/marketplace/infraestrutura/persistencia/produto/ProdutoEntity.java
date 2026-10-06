package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.categoria.CategoriaEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
public class ProdutoEntity {

    @Id
    public UUID id;

    @ManyToOne
            @JoinColumn(name = "vendedor_id", nullable = false)
    public VendedorEntity vendedor;

    @Column(nullable = false, length = 150)
    public String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    public CategoriaEntity categoria;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String descricao;

    @Column(columnDefinition = "TEXT")
    public String foto;

    @Column(nullable = false)
    public BigDecimal preco;

    @Column(nullable = false)
    public Float avaliacao;

}
