package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_vendedores")
@NoArgsConstructor
public class VendedorEntity extends UsuarioEntity{

    @Embedded
    EnderecoEntity endereco;
}
