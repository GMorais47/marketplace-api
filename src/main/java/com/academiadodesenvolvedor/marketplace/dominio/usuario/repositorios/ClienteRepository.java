package com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findById(UUID id);
    List<Endereco> findEnderecoById(UUID id);
    boolean existsByEmail(String email);
    void save(Cliente cliente);
}
