package com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CadastrarProdutoRequest(
        String nome,
        UUID categoriaId,
        String descricao,
        String foto,
        BigDecimal preco
) {
}
