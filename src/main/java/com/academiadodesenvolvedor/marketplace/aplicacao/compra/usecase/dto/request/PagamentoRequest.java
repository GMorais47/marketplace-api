package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request;

public record PagamentoRequest(
        String token,
        String documento,
        String bandeira,
        Integer parcela
) {
}