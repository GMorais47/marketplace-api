package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.UsuarioRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.UsuarioEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.UsuarioEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioEntityRepository jpaRepository;

    public UsuarioRepositoryImpl(UsuarioEntityRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        Optional<UsuarioEntity> optEntity = this.jpaRepository.findByEmail(email);
        if (optEntity.isEmpty()) return Optional.empty();

        UsuarioEntity entity = optEntity.get();

        Usuario usuario = new Usuario()
                .restaurar(entity.id, entity.perfil, entity.nome, entity.email, entity.senha);

        return Optional.of(usuario);
    }

    @Override
    public boolean existsByEmail(String email){
        return this.jpaRepository.existsByEmail(email);
    }

    @Override
    public void save(Usuario usuario){
        UsuarioEntity entity = new UsuarioEntity(
                usuario.getId(),
                usuario.getPerfil(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()
        );
        this.jpaRepository.save(entity);
    }
}
