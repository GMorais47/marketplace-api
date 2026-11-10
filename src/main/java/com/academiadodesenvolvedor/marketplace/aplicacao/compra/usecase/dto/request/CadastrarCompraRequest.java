package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;

import java.util.List;

public record CadastrarCompraRequest(
        PagamentoRequest pagamentoRequest,
        Endereco endereco,
        List<CompraProdutoRequest> produtos
) {
}
