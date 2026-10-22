package com.academiadodesenvolvedor.marketplace.infraestrutura.web.categoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CadastrarCategoriaDTO(
        @NotBlank(message = "O nome é de preenchimento obrigatório!")
        @Length(min = 3, max = 150, message = "O nome deve ter entre 3 - 150 caracteres!")
        String nome,

        @NotBlank(message = "O path é de preenchimento obrigatório!")
        @Length(min = 3, message = "O path deve ter no mínimo 3 caracteres!")
        String path,

        @NotNull(message = "O destaque é de preenchimento obrigatório!")
        Boolean destaque
) {
}
