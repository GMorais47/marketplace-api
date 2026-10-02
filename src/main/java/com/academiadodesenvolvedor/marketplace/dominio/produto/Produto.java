package com.academiadodesenvolvedor.marketplace.dominio.produto;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Produto {

    @NonNull
    private final UUID id;

    @NonNull
    private final UUID vendedorID;

    @NonNull
    private String nome;

    @NonNull
    private UUID categoriaID;

    @NonNull
    private String descricao;

    @Nullable
    private String foto;

    @NonNull
    private BigDecimal preco;

    @NonNull
    private Float avaliacao;

    public Produto(
            @NonNull UUID vendedorID,
            @NonNull String nome,
            @NonNull UUID categoriaID,
            @NonNull String descricao,
            @Nullable String foto,
            @NonNull BigDecimal preco
    ) {
        this.id = UUID.randomUUID();
        this.vendedorID = vendedorID;
        this.setNome(nome);
        this.setCategoriaID(categoriaID);
        this.setDescricao(descricao);
        this.setFoto(foto);
        this.setPreco(preco);
        this.avaliacao = new Float(0);
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setCategoriaID(@NonNull UUID categoriaID) {
        this.categoriaID = categoriaID;
    }

    public void setDescricao(@NonNull String descricao) {
        this.descricao = descricao;
    }

    public void setFoto(@Nullable String foto) {
        this.foto = foto;
    }

    public void setPreco(@NonNull BigDecimal preco) {
        this.preco = preco;
    }

    public void setAvaliacao(@NonNull Float avaliacao) {
        this.avaliacao = avaliacao;
    }
}
