package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_vendedores")
@NoArgsConstructor
public class VendedorEntity extends UsuarioEntity{

    @Embedded
    EnderecoEntity endereco;

    public VendedorEntity(
            UUID id,
            Perfil perfil,
            String nome,
            String email,
            String senha,
            EnderecoEntity endereco
    ) {
        super(id, perfil, nome, email, senha);
        this.endereco = endereco;
    }
}
