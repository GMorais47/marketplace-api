package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record CompraProdutoDTO (
        @NotNull(message = "O ID do produto é de preenchimento obrigatório!")
        @UUID(message = "O ID deve ser um UUID válido!")
        String produtoId,

        @NotNull(message = "A quantidade é de preenchimento obrigatório!")
        @Min(value = 1,message = "A quantidade mínima é 1!")
        Integer quantidade,

        @NotNull(message = "O valor é de preenchimento obrigatório!")
        @DecimalMin(value = "0.01", message = "O valor mínimo do produto deve ser R$ 0,01!")
        BigDecimal valor
){
}
