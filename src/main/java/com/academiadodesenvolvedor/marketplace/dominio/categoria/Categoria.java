package com.academiadodesenvolvedor.marketplace.dominio.categoria;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Categoria {

    @NonNull
    private final UUID id;

    @NonNull
    private String nome;

    @NonNull
    private String path;

    @Setter
    private boolean isDestaque;

    public Categoria(
            @NonNull String nome,
            @NonNull String path,
            boolean isDestaque
    ){
        this.id = UUID.randomUUID();
        this.setNome(nome);
        this.setPath(path);
        this.setDestaque(isDestaque);
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setPath(@NonNull String path) {
        this.path = path;
    }
}
