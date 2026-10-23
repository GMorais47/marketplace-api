package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto;

import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoEntityRepository jpaRepository;

    @Override
    public List<Produto> findByVendedorId(UUID id) {
        List<ProdutoEntity> entities = this.jpaRepository.findByVendedorId(id);
        return entities.stream().map(
                entity -> new Produto()
                        .restaurar(
                                entity.id,
                                entity.vendedor.id,
                                entity.nome,
                                entity.categoria.id,
                                entity.descricao,
                                entity.foto,
                                entity.preco,
                                entity.avaliacao
                        )
        ).toList();
    }

    @Override
    public List<Produto> findByCategoriaId(UUID id) {
        List<ProdutoEntity> entities = this.jpaRepository.findByCategoriaId(id);
        return entities.stream().map(
                entity -> new Produto()
                        .restaurar(
                                entity.id,
                                entity.vendedor.id,
                                entity.nome,
                                entity.categoria.id,
                                entity.descricao,
                                entity.foto,
                                entity.preco,
                                entity.avaliacao
                        )
        ).toList();
    }

    @Override
    public List<Produto> find() {
        return this.jpaRepository.findAll()
                .stream().map(
                        entity -> new Produto()
                                .restaurar(
                                        entity.id,
                                        entity.vendedorId,
                                        entity.nome,
                                        entity.categoriaId,
                                        entity.descricao,
                                        entity.foto,
                                        entity.preco,
                                        entity.avaliacao
                                )
                ).toList();
    }

    @Override
    public List<Produto> findAllById(Iterable<UUID> ids) {
        return this.jpaRepository.findAllById(ids)
                .stream().map(
                        entity -> new Produto()
                                .restaurar(
                                        entity.id,
                                        entity.vendedorId,
                                        entity.nome,
                                        entity.categoriaId,
                                        entity.descricao,
                                        entity.foto,
                                        entity.preco,
                                        entity.avaliacao
                                )
                ).toList();
    }

    @Override
    public void save(Produto produto) {
        ProdutoEntity entity = new ProdutoEntity(
                produto.getId(),
                produto.getVendedorID(),
                produto.getNome(),
                produto.getCategoriaID(),
                produto.getDescricao(),
                produto.getFoto(),
                produto.getPreco(),
                produto.getAvaliacao()
        );
        this.jpaRepository.save(entity);
    }
}
