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
        String regex = "[a-z0-9 ]+";

        String trim = path.trim().toLowerCase();

        if(!trim.matches(regex) || trim.length() < 3){
            throw new IllegalArgumentException("Path inválido!");
        }

        this.path = trim.replace(" ", "-");
    }
}
