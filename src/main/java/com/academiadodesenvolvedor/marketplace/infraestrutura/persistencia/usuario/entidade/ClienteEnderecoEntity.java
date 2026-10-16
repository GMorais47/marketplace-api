package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_cliente_enderecos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteEnderecoEntity {
    @Id
    UUID id;

    @ManyToOne
            @JoinColumn(name = "cliente_id")
    ClienteEntity cliente;

    @Embedded
    EnderecoEntity endereco;
}
