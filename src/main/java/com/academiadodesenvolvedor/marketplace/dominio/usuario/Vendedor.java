package com.academiadodesenvolvedor.marketplace.dominio.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
public class Vendedor extends Usuario {

    @NonNull @Getter @Setter
    private Endereco endereco;

    public Vendedor(
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        super(Perfil.VENDEDOR, nome, email, senha);
    }

    private Vendedor(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        super(id,perfil, nome, email, senha);
    }

    public Vendedor restaurar(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
       return new Vendedor(
                id,
                perfil,
                nome,
                email,
                senha
        );
    }
}
