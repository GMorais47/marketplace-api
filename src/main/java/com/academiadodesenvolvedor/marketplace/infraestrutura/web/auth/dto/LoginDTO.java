package com.academiadodesenvolvedor.marketplace.infraestrutura.web.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O e-mail é de preenchimento obrigatório!")
        @Email(message = "E-mail é inválido!")
        String email,

        @NotBlank(message = "A senha é de preenchimento obrigatório!")
        String senha
) {
}
