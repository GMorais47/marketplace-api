package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.categoria;

import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.repositorios.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaEntityRepository jpaRepository;

    @Override
    public List<Categoria> find() {
        List<CategoriaEntity> entities = this.jpaRepository.findAll();

        return entities.stream().map(
                entity -> new Categoria()
                        .restaurar(entity.id, entity.nome, entity.path, entity.isDestaque)
        ).toList();
    }

    @Override
    public Optional<Categoria> findById(UUID id){
        Optional<CategoriaEntity> entity = this.jpaRepository.findById(id);
        if(entity.isEmpty()) return Optional.empty();

        Categoria categoria = new Categoria()
                .restaurar(entity.get().id, entity.get().nome, entity.get().path, entity.get().isDestaque);

        return Optional.of(categoria);
    }

    @Override
    public List<Categoria> findByIsDestaque(){
        List<CategoriaEntity> entities = this.jpaRepository.findByIsDestaque(true);

        return entities.stream().map(
                entity -> new Categoria()
                        .restaurar(entity.id, entity.nome, entity.path, entity.isDestaque)
        ).toList();
    }

    @Override
    public boolean existsByPath(String path){
        return this.jpaRepository.existsByPath(path);
    }

    @Override
    public void save(Categoria categoria){
        CategoriaEntity entity = new CategoriaEntity(
                categoria.getId(),
                categoria.getNome(),
                categoria.getPath(),
                categoria.isDestaque()
        );
        this.jpaRepository.save(entity);
    }

}
