package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request;

public record CartaoRequest(
        String numero,
        String titular,
        String expiracao,
        String codigo_seguranca
) {
}
