package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.ConflictException;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarAdminRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase.dto.CriarUsuarioResponse;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarAdminUseCase {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public CriarUsuarioResponse execute(CriarAdminRequest request){
        Usuario admin = new Usuario(
                Perfil.ADMIN,
                request.nome(),
                request.email(),
                request.senha()
        );
        if (repository.existsByEmail(admin.getEmail())){
            throw new ConflictException("E-mail já registrado!");
        }

        String hash = this.passwordEncoder.encode(request.senha());

        admin.setSenha(hash);

        this.repository.save(admin);

        return new CriarUsuarioResponse(admin.getId(), admin.getNome());
    }
}
