package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.ConflictException;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarClienteRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarClienteResponse;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarVendedorResponse;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Vendedor;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.ClienteRepositoryImpl;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.VendedorRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CriarClienteUseCase {
    private final ClienteRepositoryImpl repository;
    private final PasswordEncoder passwordEncoder;

    public CriarClienteResponse execute(CriarClienteRequest request){
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

        return new CriarClienteResponse(cliente.getId(), cliente.getNome());
    }
}
