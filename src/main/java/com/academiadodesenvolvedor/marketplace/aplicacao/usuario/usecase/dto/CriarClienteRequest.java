package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;

import java.time.LocalDate;

public record CriarClienteRequest(
        String nome,
        String email,
        String senha,
        String documento,
        LocalDate dataNascimento,
        String telefone,
        Endereco endereco
) {
}
