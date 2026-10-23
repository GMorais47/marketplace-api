package com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessao -> sessao.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/vendedores").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/clientes").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/produtos/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categorias/**").permitAll()

                        .requestMatchers(HttpMethod.POST,"/api/categorias").hasRole(Perfil.ADMIN.name())
                        .requestMatchers(HttpMethod.POST,"/api/produtos").hasRole(Perfil.VENDEDOR.name())
                        .requestMatchers(HttpMethod.POST, "/api/pedidos").hasRole(Perfil.CLIENTE.name())

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
