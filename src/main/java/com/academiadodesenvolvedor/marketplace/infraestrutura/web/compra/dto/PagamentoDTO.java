package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoDTO(
        //@NotNull(message = "O cartao é de preenchimento obrigatório!" )
       // @Valid
        //CartaoDTO cartao,

        @NotBlank(message = "O documento é de preenchimento obrigatório!")
        //@Length(min = 11, max = 11, message = "O documento deve ter 11 caracteres!")
        String documento,

        @NotBlank(message = "A bandeira é de preenchimento obrigatório!")
        //@Length(min = 11, max = 11, message = "O documento deve ter 11 caracteres!")
        String bandeira,

        @NotBlank(message = "O token é de preenchimento obrigatório!")
        String token,

        @NotNull(message = "A parcela é de preenchimento obrigatório!")
        @Min(value = 1, message = "O valor mínimo da parcela é 1!" )
        @Max(value = 12, message = "O valor máximo da parcela é 12!" )
        Integer parcela
) { }