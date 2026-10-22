package com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CriarAdminDTO(
        @NotBlank(message = "O nome é de preenchimento obrigatório!")
        @Length(min = 3, max = 150, message = "O nome deve ter entre 3 - 150 caracteres!")
        String nome,

        @NotBlank(message = "O e-mail é de preenchimento obrigatório!")
        @Email(message = "E-mail é inválido!")
        String email,

        @NotBlank(message = "A senha é de preenchimento obrigatório!")
        @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres!")
        String senha
) {
}
