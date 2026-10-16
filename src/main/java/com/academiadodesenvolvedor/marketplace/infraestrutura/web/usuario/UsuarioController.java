package com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario;

import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.CriarVendedorUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorResponse;
import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario.dto.CriarVendedorDTO;
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
    public ResponseEntity<ApiResponse<CriarVendedorResponse>> criarVendedor(@RequestBody @Valid CriarVendedorDTO dto) {
        Endereco endereco = new Endereco(
                dto.endereco().cep(),
                dto.endereco().rua(),
                dto.endereco().numero(),
                dto.endereco().bairro(),
                dto.endereco().complemento(),
                dto.endereco().cidade(),
                dto.endereco().estado()
        );

        CriarVendedorRequest request = new CriarVendedorRequest(
                dto.nome(),
                dto.email(),
                dto.senha(),
                endereco
        );

        CriarVendedorResponse vendedorResponse = this.criarVendedorUseCase.execute(request);
        ApiResponse<CriarVendedorResponse> response = ApiResponse.sucesso("Vendedor cadastrado com sucesso!", vendedorResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
