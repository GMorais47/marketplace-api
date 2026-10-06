package com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    void save(Usuario usuario);
}
