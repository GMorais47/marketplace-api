package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CartaoDTO(
        @NotBlank(message = "O numero é de preenchimento obrigatório!")
        @Length(min = 16, max = 16, message = "O numero deve ter 16 caracteres!")
        String numero,

        @NotBlank(message = "O titular é de preenchimento obrigatório!")
        @Length(min = 3, message = "O titula deve ter no mínimo 3 caracteres!")
        String titular,

        @NotBlank(message = "A expiracao é de preenchimento obrigatório!")
        @Length(min = 5, max = 5, message = "O expiracao deve ter 5 caracteres!")
        String expiracao,

        @NotBlank(message = "O código de segurança é de preenchimento obrigatório!")
        @Length(min = 3, max = 3, message = "O código de segurança deve ter 3 caracteres!")
        String codigo_seguranca
) {
}
