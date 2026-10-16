package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.ClienteRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.ClienteEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ClienteRepositoryImpl implements ClienteRepository {

    private final ClienteEntityRepository repository;

    @Override
    public Optional<Cliente> findByEmail(String email) {
        Optional<ClienteEntity> optEntity = this.repository.findByEmail(email);
        if (optEntity.isEmpty()) return Optional.empty();

        ClienteEntity entity = optEntity.get();

        List<Endereco> enderecos = new ArrayList<>();

        for (ClienteEnderecoEntity enderecoEntity : entity.enderecos) {
            Endereco endereco = new Endereco(
                    enderecoEntity.getEndereco().cep,
                    enderecoEntity.getEndereco().rua,
                    enderecoEntity.getEndereco().numero,
                    enderecoEntity.getEndereco().bairro,
                    enderecoEntity.getEndereco().complemento,
                    enderecoEntity.getEndereco().cidade,
                    enderecoEntity.getEndereco().estado
            );
            enderecos.add(endereco);
        }

        Cliente cliente = new Cliente()
                .restaurar(
                        entity.id,
                        entity.perfil,
                        entity.nome,
                        entity.email,
                        entity.senha,
                        entity.documento,
                        entity.dataNascimento,
                        entity.telefone,
                        enderecos
                );

        return Optional.of(cliente);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.repository.existsByEmail(email);
    }

    @Override
    public void save(Cliente cliente) {

        ClienteEntity entity = new ClienteEntity(
                cliente.getId(),
                cliente.getPerfil(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getDocumento(),
                cliente.getDataNascimento(),
                cliente.getTelefone(),
                new ArrayList<>()
        );

        List<ClienteEnderecoEntity> enderecosEntities = new ArrayList<>();

        for (Endereco endereco : cliente.getEnderecos()) {
            EnderecoEntity enderecoEntity1 = new EnderecoEntity(
                    endereco.getCep(),
                    endereco.getRua(),
                    endereco.getNumero(),
                    endereco.getBairro(),
                    endereco.getComplemento(),
                    endereco.getCidade(),
                    endereco.getEstado()
            );

            enderecosEntities.add(new ClienteEnderecoEntity(
                    UUID.randomUUID(),
                    entity,
                    enderecoEntity1
            ));
        }

        entity.enderecos = enderecosEntities;

        this.repository.save(entity);
    }
}
