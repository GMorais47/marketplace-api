package com.academiadodesenvolvedor.marketplace.dominio.categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class Categoria {

    @NonNull
    private UUID id;

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
    ) {
        this.id = UUID.randomUUID();
        this.setNome(nome);
        this.setPath(path);
        this.setDestaque(isDestaque);
    }

    private Categoria(@NonNull UUID id, @NonNull String nome, @NonNull String path, boolean isDestaque) {
        this.id = id;
        this.nome = nome;
        this.path = path;
        this.isDestaque = isDestaque;
    }

    public Categoria restaurar(@NonNull UUID id, @NonNull String nome, @NonNull String path, boolean isDestaque) {
        return new Categoria(id, nome, path, isDestaque);
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    public void setPath(@NonNull String path) {
        this.path = path;
    }
}
