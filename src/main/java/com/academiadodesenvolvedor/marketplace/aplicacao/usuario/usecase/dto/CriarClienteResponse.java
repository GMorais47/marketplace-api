package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

import java.util.UUID;

public record CriarClienteResponse(
        UUID id,
        String nome
) {
}
