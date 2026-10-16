package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_clientes")
@NoArgsConstructor
public class ClienteEntity extends UsuarioEntity {

    @Column(nullable = false, length = 11)
    public String documento;

    @Column(name = "data_nascimento")
    public LocalDate dataNascimento;

    @Column(length = 11)
    public String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ClienteEnderecoEntity> enderecos = new ArrayList<>();

    public ClienteEntity(
            UUID id,
            Perfil perfil,
            String nome,
            String email,
            String senha,
            String documento,
            LocalDate dataNascimento,
            String telefone,
            List<ClienteEnderecoEntity> enderecos
    ) {
        super(id, perfil, nome, email, senha);
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }
}
