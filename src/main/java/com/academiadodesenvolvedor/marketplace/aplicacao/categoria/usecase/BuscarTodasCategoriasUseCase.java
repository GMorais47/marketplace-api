package com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase;

import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.repositorios.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuscarTodasCategoriasUseCase {

    private final CategoriaRepository repository;

    public List<Categoria> execute(){
        return this.repository.find();
    }
}
