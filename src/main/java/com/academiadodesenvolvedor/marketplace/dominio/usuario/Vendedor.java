package com.academiadodesenvolvedor.marketplace.dominio.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import lombok.NonNull;

public class Vendedor extends Usuario {

    public Vendedor(
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha
    ) {
        super(Perfil.VENDEDOR, nome, email, senha);
    }
}
