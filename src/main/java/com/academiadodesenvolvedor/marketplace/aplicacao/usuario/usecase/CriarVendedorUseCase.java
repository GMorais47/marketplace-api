package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.VendedorRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarVendedorUseCase {

    private final VendedorRepositoryImpl repository;

    public void execute(){

    }
}
