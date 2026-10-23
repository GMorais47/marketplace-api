package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto;

import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.categoria.CategoriaEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_produtos")
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    public UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id", insertable = false, updatable = false, nullable = false)
    public VendedorEntity vendedor;

    @Column(name = "vendedor_id", nullable = false)
    public UUID vendedorId;

    @Column(nullable = false, length = 150)
    public String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false, nullable = false)
    public CategoriaEntity categoria;

    @Column(name = "categoria_id", nullable = false)
    public UUID categoriaId;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String descricao;

    @Column(columnDefinition = "TEXT")
    public String foto;

    @Column(nullable = false)
    public BigDecimal preco;

    @Column(nullable = false)
    public Float avaliacao;

    public ProdutoEntity(UUID id, UUID vendedorId, String nome, UUID categoriaId, String descricao, String foto, BigDecimal preco, Float avaliacao) {
        this.id = id;
        this.vendedorId = vendedorId;
        this.nome = nome;
        this.categoriaId = categoriaId;
        this.descricao = descricao;
        this.foto = foto;
        this.preco = preco;
        this.avaliacao = avaliacao;
    }

    public static ProdutoEntity fromDomain(Produto produto) {
        return new ProdutoEntity(
                produto.getId(),
                produto.getVendedorID(),
                produto.getNome(),
                produto.getCategoriaID(),
                produto.getDescricao(),
                produto.getFoto(),
                produto.getPreco(),
                produto.getAvaliacao()
        );
    }
}
