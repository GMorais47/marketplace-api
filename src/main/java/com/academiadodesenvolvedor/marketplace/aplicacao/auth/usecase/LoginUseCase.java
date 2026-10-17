package com.academiadodesenvolvedor.marketplace.aplicacao.auth.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.UnauthorizedExeception;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.UsuarioRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUseCase {

    public final UsuarioRepository repository;
    public final PasswordEncoder passwordEncoder;
    public final TokenService tokenService;

    public String Execute(String email, String senha){
        try {
            Optional<Usuario> optionalUsuario = this.repository.findByEmail(email);
            if(optionalUsuario.isEmpty()) throw new RuntimeException();

            Usuario usuario = optionalUsuario.get();

            boolean match = usuario.match(senha, this.passwordEncoder);
            if(!match) throw new RuntimeException();

            return this.tokenService.gerarToken(usuario);
        } catch (Exception e) {
            throw new UnauthorizedExeception("Usuário ou senha inválidos!");
        }
    }
}
