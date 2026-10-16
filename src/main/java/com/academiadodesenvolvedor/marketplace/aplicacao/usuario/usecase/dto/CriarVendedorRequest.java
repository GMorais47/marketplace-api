package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;

public record CriarVendedorRequest(
        String nome,
        String email,
        String senha,
        Endereco endereco
) { }
