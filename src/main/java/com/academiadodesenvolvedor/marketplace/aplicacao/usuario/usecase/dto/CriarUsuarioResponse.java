package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

import java.util.UUID;

public record CriarUsuarioResponse(
        UUID id,
        String nome
) {}