package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto;

import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import javax.annotation.RegEx;
import java.util.List;

public record CadastrarCompraDTO(
        @NotNull(message = "O endereço é de preenchimento obrigatório!")
        @Valid
        EnderecoDTO endereco,

        @NotBlank(message = "O token é de preenchimento obrigatório!")
        String token,

        @NotNull(message = "A parcela é de preenchimento obrigatório!")
        @Min(value = 1, message = "O valor mínimo da parcela é 1!" )
        @Max(value = 12, message = "O valor máximo da parcela é 12!" )
        Integer parcela,

        @NotBlank(message = "A bandeira é de preenchimento obrigatório!")
        @Pattern(regexp = "^(?i)(visa|mastercard)$", message = "Apenas cartões Visa e Mastercard são aceitos!")
        String bandeira,

        @NotEmpty(message = "Deve informar pelo menos um produto!")
        @Valid
        List<CompraProdutoDTO> produtos
) {
}
