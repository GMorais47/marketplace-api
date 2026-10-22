package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.ConflictException;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarUsuarioResponse;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorRequest;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Vendedor;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.VendedorRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarVendedorUseCase {

    private final VendedorRepositoryImpl repository;
    private final PasswordEncoder passwordEncoder;

    public CriarUsuarioResponse execute(CriarVendedorRequest request){
        Vendedor vendedor = new Vendedor(
                request.nome(),
                request.email(),
                request.senha(),
                request.endereco()
        );
        if (repository.existsByEmail(vendedor.getEmail())){
            throw new ConflictException("E-mail já registrado!");
        }

        String hash = this.passwordEncoder.encode(request.senha());

        vendedor.setSenha(hash);

        this.repository.save(vendedor);

        return new CriarUsuarioResponse(vendedor.getId(), vendedor.getNome());
    }
}
