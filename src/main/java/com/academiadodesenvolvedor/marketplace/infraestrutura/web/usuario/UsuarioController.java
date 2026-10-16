package com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario;

import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.CriarClienteUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.CriarVendedorUseCase;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarClienteRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarClienteResponse;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorResponse;
import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario.dto.CriarClienteDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario.dto.CriarVendedorDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final CriarClienteUseCase criarClienteUseCase;

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

    @PostMapping("/clientes")
    public ResponseEntity<ApiResponse<CriarClienteResponse>> criarCliente(@RequestBody @Valid CriarClienteDTO dto) {
        Endereco endereco = new Endereco(
                dto.endereco().cep(),
                dto.endereco().rua(),
                dto.endereco().numero(),
                dto.endereco().bairro(),
                dto.endereco().complemento(),
                dto.endereco().cidade(),
                dto.endereco().estado()
        );

        CriarClienteRequest request = new CriarClienteRequest(
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.documento(),
                dto.dataNascimento(),
                dto.telefone(),
                endereco
        );

        CriarClienteResponse clienteResponse = this.criarClienteUseCase.execute(request);
        ApiResponse<CriarClienteResponse> response = ApiResponse.sucesso("Cliente cadastrado com sucesso!", clienteResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
