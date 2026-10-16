package com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.jspecify.annotations.Nullable;

public record EnderecoDTO(
        @NotBlank(message = "O CEP é de preenchimento obrigatório!")
        @Length(min = 8, max = 8, message = "O CEP deve ter 8 caracteres!")
        String cep,

        @NotBlank(message = "A rua é de preenchimento obrigatório!")
        String rua,

        @NotBlank(message = "O número é de preenchimento obrigatório!")
        String numero,

        @NotBlank(message = "O bairro é de preenchimento obrigatório!")
        String bairro,

        String complemento,

        @NotBlank(message = "A cidade é de preenchimento obrigatório!")
        String cidade,

        @NotBlank(message = "O estado é de preenchimento obrigatório!")
        @Length(min = 2, max = 2, message = "O estado deve ter 2 caracteres!")
        String estado
) {
}
