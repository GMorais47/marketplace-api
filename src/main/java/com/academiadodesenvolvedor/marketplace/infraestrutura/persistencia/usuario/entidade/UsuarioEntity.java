package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    public UUID id;

    @Enumerated(EnumType.STRING)
            @Column(nullable = false)
    public Perfil perfil;

    @Column(length = 150, nullable = false)
    public String nome;

    @Column(unique = true, nullable = false)
    public String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    public String senha;
}
