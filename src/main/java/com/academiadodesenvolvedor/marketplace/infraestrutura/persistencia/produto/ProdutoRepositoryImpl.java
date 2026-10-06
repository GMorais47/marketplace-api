package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto;

import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoEntityRepository jpaRepository;

    @Override
    public List<Produto> findByVendedorId(UUID id) {
        List<ProdutoEntity> entities = this.jpaRepository.findByVendedorID(id);
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
        List<ProdutoEntity> entities = this.jpaRepository.findByCategoriaID(id);
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
}
