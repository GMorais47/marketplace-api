package com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto;

import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;

import java.util.UUID;

public record Auth(
        UUID id,
        Perfil perfil
) {
}
