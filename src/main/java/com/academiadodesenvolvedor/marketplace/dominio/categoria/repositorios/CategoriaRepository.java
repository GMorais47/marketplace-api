package com.academiadodesenvolvedor.marketplace.dominio.categoria.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository {
    List<Categoria> find();
    Optional<Categoria> findById(UUID id);
    List<Categoria> findByIsDestaque();
    boolean existsByPath(String path);
    void save(Categoria categoria);
}
