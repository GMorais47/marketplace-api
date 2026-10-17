package com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String segredo;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.create()
                    .withIssuer("marketplace-api")
                    .withSubject(usuario.getId().toString())
                    .withClaim("perfil",usuario.getPerfil().toString())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .withNotBefore(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        }catch (JWTCreationException ex){
            throw new RuntimeException("Não foi possivel gerar o token de acesso!");
        }
    }
}
