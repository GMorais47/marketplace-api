package com.academiadodesenvolvedor.marketplace.modelo.usuario;

import com.academiadodesenvolvedor.marketplace.modelo.shared.EnderecoEntity;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_cliente_enderecos")
@NoArgsConstructor
public class ClienteEnderecoEntity {
    @Id
    UUID id;

    @ManyToOne
            @JoinColumn(name = "cliente_id")
    ClienteEntity cliente;

    @Embedded
    EnderecoEntity endereco;
}
