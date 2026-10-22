package com.academiadodesenvolvedor.marketplace.infraestrutura.web.categoria;

import com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase.BuscarTodasCategoriasUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase.CadastrarCategoriaUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase.dto.CriarCategoriaRequest;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.categoria.dto.CadastrarCategoriaDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@AllArgsConstructor
public class CategoriaController {

    private final BuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;
    private final CadastrarCategoriaUseCase cadastrarCategoriaUseCase;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Categoria>>> buscarTodasCategorias() {
        List<Categoria> categorias = this.buscarTodasCategoriasUseCase.execute();
        ApiResponse<List<Categoria>> response = ApiResponse.sucesso("Categorias recuperadas com sucesso", categorias);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Categoria>> cadastrarCategoria(@RequestBody @Valid CadastrarCategoriaDTO dto) {
        CriarCategoriaRequest request = new CriarCategoriaRequest(dto.nome(), dto.path(), dto.destaque());
        Categoria categoria = this.cadastrarCategoriaUseCase.execute(request);
        ApiResponse<Categoria> response = ApiResponse.sucesso("Categoria cadastrada com sucesso!", categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
