package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

import java.util.UUID;

public record CriarVendedorResponse(
        UUID id,
        String nome
) {
}
