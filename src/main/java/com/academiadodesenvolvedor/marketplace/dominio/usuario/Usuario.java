package com.academiadodesenvolvedor.marketplace.dominio.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import lombok.Getter;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class Usuario {

    @Getter
    @NonNull
    private UUID id;

    @Getter
    @NonNull
    private Perfil perfil;

    @Getter
    @NonNull
    private String nome;

    @Getter
    @NonNull
    private String email;

    @Getter
    @NonNull
    private String senha;

    public Usuario(
            @Nullable Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        this.id = UUID.randomUUID();
        this.perfil = perfil == null ? Perfil.CLIENTE : perfil;
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
    }

    private Usuario(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        this.id = id;
        this.perfil = perfil;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario() {}

    public Usuario restaurar(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        return new Usuario(id, perfil, nome, email, senha);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean match(String senha, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(senha, this.senha);
    }
}
