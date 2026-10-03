package com.academiadodesenvolvedor.marketplace.modelo.usuario;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
public class ClienteEntity extends UsuarioEntity {

    @Column(nullable = false, length = 14)
    String documento;

    @Column(name = "data_nascimento")
    LocalDate dataNascimento;

    @Column(length = 11)
    String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ClienteEnderecoEntity> enderecos = new ArrayList<>();
}
