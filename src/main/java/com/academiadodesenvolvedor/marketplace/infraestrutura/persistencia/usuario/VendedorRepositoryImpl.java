package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Vendedor;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.VendedorRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.UsuarioEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.VendedorEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class VendedorRepositoryImpl implements VendedorRepository {

    private final VendedorEntityRepository jpaRepository;

    @Override
   public Optional<Vendedor> findByEmail(String email){
        Optional<VendedorEntity> optEntity = this.jpaRepository.findByEmail(email);
        if (optEntity.isEmpty()) return Optional.empty();

        VendedorEntity entity = optEntity.get();

        Vendedor vendedor = new Vendedor()
                .restaurar(entity.id, entity.perfil, entity.nome, entity.email, entity.senha);

        return Optional.of(vendedor);
   }

    @Override
    public boolean existsByEmail(String email){
        return this.existsByEmail(email);
    }

    @Override
    public void save(Vendedor vendedor){
        Endereco endereco = vendedor.getEndereco();

        EnderecoEntity enderecoEntity = new EnderecoEntity(
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado()
        );

        VendedorEntity entity = new VendedorEntity(
                vendedor.getId(),
                vendedor.getPerfil(),
                vendedor.getNome(),
                vendedor.getEmail(),
                vendedor.getSenha(),
                enderecoEntity
        );
        this.jpaRepository.save(entity);
    }
}
