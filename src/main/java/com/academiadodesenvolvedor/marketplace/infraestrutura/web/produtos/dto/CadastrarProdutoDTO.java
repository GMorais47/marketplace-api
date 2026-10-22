package com.academiadodesenvolvedor.marketplace.infraestrutura.web.produtos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record CadastrarProdutoDTO(
        @NotBlank(message = "O nome é de preenchimento obrigatório!")
        @Length(min = 3, max = 150, message = "O nome deve ter entre 3 - 150 caracteres!")
        String nome,

        @NotNull(message = "O ID da categoria é de preenchimento obrigatório!")
        @UUID(message = "O ID deve ser um UUID válido!")
        String categoriaId,

        @NotBlank(message = "A descrição é de preenchimento obrigatório!")
        String descricao,

        @NotBlank(message = "A foto é de preenchimento obrigatório!")
        @URL(message = "A foto deve ser uma URL válida!")
        String foto,

        @NotNull(message = "O preço é de preenchimento obrigatório!")
        @DecimalMin(value = "0.01", message = "O preço mínimo do produto deve ser R$ 0,01!")
        BigDecimal preco
) {
}
