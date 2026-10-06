package com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {
    List<Produto> findByVendedorId(UUID id);
    List<Produto> findByCategoriaId(UUID id);
    List<Produto> find();
}
