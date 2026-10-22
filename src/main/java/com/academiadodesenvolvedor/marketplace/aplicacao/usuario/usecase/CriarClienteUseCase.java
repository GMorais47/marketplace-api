package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.ConflictException;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarClienteRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarUsuarioResponse;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.ClienteRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarClienteUseCase {
    private final ClienteRepositoryImpl repository;
    private final PasswordEncoder passwordEncoder;

    public CriarUsuarioResponse execute(CriarClienteRequest request){
        Cliente cliente = new Cliente(
                request.nome(),
                request.email(),
                request.senha(),
                request.documento(),
                request.dataNascimento(),
                request.telefone()
        );
        if (repository.existsByEmail(cliente.getEmail())){
            throw new ConflictException("E-mail já registrado!");
        }

        cliente.adicionarEndereco(request.endereco());

        String hash = this.passwordEncoder.encode(request.senha());

        cliente.setSenha(hash);

        this.repository.save(cliente);

        return new CriarUsuarioResponse(cliente.getId(), cliente.getNome());
    }
}
