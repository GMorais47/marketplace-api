package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;

import java.util.List;

public record CadastrarCompraRequest(
        Endereco endereco,
        List<CompraProdutoRequest> produtos
) {
}
