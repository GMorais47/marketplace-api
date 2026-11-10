package com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra;

import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.CadastrarCompraUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.CadastrarCompraRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.CartaoRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.PagamentoRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.response.CadastrarCompraResponse;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.CompraProdutoRequest;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.compra.dto.CadastrarCompraDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/compras")
@AllArgsConstructor
public class CompraController {

    private final CadastrarCompraUseCase cadastrarCompraUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<CadastrarCompraResponse>> realizar(@RequestBody @Valid CadastrarCompraDTO dto, @AuthenticationPrincipal Auth auth) {
        List<CompraProdutoRequest> produtos = dto
                .produtos()
                .stream()
                .map(prd -> new CompraProdutoRequest(
                        UUID.fromString(prd.produtoId()),
                        prd.quantidade(),
                        prd.valor()
                )).toList();

        CadastrarCompraRequest request = new CadastrarCompraRequest(
                new PagamentoRequest(
                        dto.pagamento().token(),
                        dto.pagamento().documento(),
                        dto.pagamento().bandeira(),
                        dto.pagamento().parcela()
                ),
                dto.endereco().toDomain(),
                produtos
        );

        CadastrarCompraResponse res = this.cadastrarCompraUseCase.execute(request, auth);
        ApiResponse<CadastrarCompraResponse> response = ApiResponse.sucesso("Compra realizada com sucesso!", res);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
