package com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.Usuario;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

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
                    .withClaim("perfil", usuario.getPerfil().toString())
                    .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                    .withNotBefore(LocalDateTime.now().toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Não foi possivel gerar o token de acesso!");
        }
    }

    public Auth validar(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("marketplace-api")
                    .build()
                    .verify(token);

            String strgPeril = decodedJWT.getClaim("perfil").asString();
            Perfil perfil = strgPeril != null ? Perfil.valueOf(strgPeril) : null;

            if(perfil == null){
                return null;
            }

            return new Auth(
                    UUID.fromString(decodedJWT.getSubject()),
                    perfil
            );
        } catch (Exception ex) {
            return null;
        }
    }
}
