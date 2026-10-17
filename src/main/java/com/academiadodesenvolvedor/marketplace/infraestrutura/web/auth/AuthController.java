package com.academiadodesenvolvedor.marketplace.infraestrutura.web.auth;

import com.academiadodesenvolvedor.marketplace.aplicacao.auth.usecase.LoginUseCase;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.auth.dto.LoginDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.auth.dto.LoginResponseDTO;
import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginUseCase useCase;

    @PostMapping
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@RequestBody @Valid LoginDTO dto) {
        String token = this.useCase.Execute(dto.email(), dto.senha());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse
                        .sucesso("Login realizado com sucesso", loginResponseDTO));
    }
}
