package com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase.dto;

public record CriarCategoriaRequest(
        String nome,
        String path,
        boolean isDestaque
) {}
