package com.academiadodesenvolvedor.marketplace.modelo.usuario;

import com.academiadodesenvolvedor.marketplace.modelo.shared.EnderecoEntity;
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
