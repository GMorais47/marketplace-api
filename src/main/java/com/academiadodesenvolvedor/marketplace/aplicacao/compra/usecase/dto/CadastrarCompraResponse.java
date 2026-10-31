package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto;

import java.util.UUID;

public record CadastrarCompraResponse(
        UUID compraId,
        String pagamentoId,
        String status
) {
}
