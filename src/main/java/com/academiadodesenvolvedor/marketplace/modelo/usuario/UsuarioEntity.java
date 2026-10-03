package com.academiadodesenvolvedor.marketplace.modelo.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    UUID id;

    @Enumerated(EnumType.STRING)
            @Column(nullable = false)
    Perfil perfil;

    @Column(length = 150, nullable = false)
    String nome;

    @Column(unique = true, nullable = false)
    String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    String senha;
}
