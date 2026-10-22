package com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.categoria.usecase.dto.CriarCategoriaRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.exception.ConflictException;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.repositorios.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CadastrarCategoriaUseCase {

    private final CategoriaRepository repository;

    public Categoria execute(CriarCategoriaRequest request) {
        Categoria categoria = new Categoria(request.nome(),request.path(),request.isDestaque());
        if(this.repository.existsByPath(categoria.getPath())){
            throw new ConflictException("Path já cadastrado!");
        }

        this.repository.save(categoria);

        return categoria;
    }
}
