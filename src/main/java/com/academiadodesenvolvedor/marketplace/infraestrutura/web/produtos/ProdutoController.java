package com.academiadodesenvolvedor.marketplace.infraestrutura.web.produtos;

import com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.BuscarTodosProdutos;
import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final BuscarTodosProdutos buscarTodosProdutos;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Produto>>> buscarTodosProdutos() {
        List<Produto> produtos = this.buscarTodosProdutos.Execute();
        ApiResponse<List<Produto>> response = ApiResponse.sucesso("Produtos recuperados com sucesso!", produtos);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
