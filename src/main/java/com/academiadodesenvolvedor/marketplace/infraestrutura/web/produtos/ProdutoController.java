package com.academiadodesenvolvedor.marketplace.infraestrutura.web.produtos;

import com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.BuscarTodosProdutos;
import com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.CadastrarProdutoUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.dto.CadastrarProdutoRequest;
import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.produtos.dto.CadastrarProdutoDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final BuscarTodosProdutos buscarTodosProdutos;
    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Produto>>> buscarTodosProdutos() {
        List<Produto> produtos = this.buscarTodosProdutos.Execute();
        ApiResponse<List<Produto>> response = ApiResponse.sucesso("Produtos recuperados com sucesso!", produtos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Produto>> cadastrarProduto(
            @RequestBody @Valid CadastrarProdutoDTO dto,
            @AuthenticationPrincipal Auth auth
    ) {
        CadastrarProdutoRequest request = new CadastrarProdutoRequest(
                dto.nome(),
                UUID.fromString(dto.categoriaId()),
                dto.descricao(),
                dto.foto(),
                dto.preco()
        );
        Produto produto = this.cadastrarProdutoUseCase.execute(request, auth);
        ApiResponse<Produto> response = ApiResponse.sucesso("Produto cadastrado com sucesso!", produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
