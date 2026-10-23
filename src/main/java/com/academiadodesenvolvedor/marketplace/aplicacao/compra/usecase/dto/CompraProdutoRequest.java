package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CompraProdutoRequest(
        UUID produtoId,
        Integer quantidade,
        BigDecimal valor
) {
}
