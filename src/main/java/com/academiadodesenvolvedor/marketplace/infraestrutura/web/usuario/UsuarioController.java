package com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario;

import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.CriarVendedorUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final CriarVendedorUseCase criarVendedorUseCase;

    @PostMapping("/vendedores")
    public ResponseEntity<CriarVendedorResponse> criarVendedor(@RequestBody @Valid CriarVendedorRequest dto){
        CriarVendedorRequest request = new CriarVendedorRequest(dto.nome(), dto.email(), dto.senha());
        CriarVendedorResponse response = this.criarVendedorUseCase.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

// POST localhost:8080/api/usuarios/vendedores
