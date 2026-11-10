package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto;

import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import javax.annotation.RegEx;
import java.util.List;

public record CadastrarCompraDTO(
        @NotNull(message = "O pagamento é de preenchimento obrigatório" )
        @Valid
        PagamentoDTO pagamento,

        @NotNull(message = "O endereço é de preenchimento obrigatório!" )
        @Valid
        EnderecoDTO endereco,

        @NotEmpty(message = "Deve informar pelo menos um produto!" )
        @Valid
        List<CompraProdutoDTO> produtos
) {
}
