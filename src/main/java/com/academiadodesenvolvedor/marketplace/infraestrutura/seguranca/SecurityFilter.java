package com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca;

import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = (authHeader != null && authHeader.startsWith("Bearer")) ?
                authHeader.replace("Bearer ", "").trim()
                : null;

        if (token != null) {
            Auth auth = tokenService.validar(token);

            if (auth != null) {
                var roles = List.of(new SimpleGrantedAuthority("ROLE_" + auth.perfil().name()));
                var authentication = new UsernamePasswordAuthenticationToken(
                        auth,
                        null,
                        roles
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request,response);
    }
}
