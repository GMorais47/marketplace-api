package com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findByEmail(String email);
    boolean existsByEmail(String email);
    void save(Cliente cliente);
}
