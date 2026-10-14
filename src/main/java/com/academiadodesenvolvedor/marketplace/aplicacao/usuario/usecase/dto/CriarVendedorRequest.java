package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

public record CriarVendedorRequest(
        String nome,
        String email,
        String senha
) { }
