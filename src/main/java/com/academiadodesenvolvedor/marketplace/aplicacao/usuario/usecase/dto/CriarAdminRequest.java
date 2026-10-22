package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

public record CriarAdminRequest(
        String nome,
        String email,
        String senha
) {
}
