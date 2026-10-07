package com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Vendedor;

import java.util.Optional;

public interface VendedorRepository {
    Optional<Vendedor> findByEmail(String email);
    boolean existsByEmail(String email);
    void save(Vendedor vendedor);
}
