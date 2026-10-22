package com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.NotFoundException;
import com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase.dto.CadastrarProdutoRequest;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.Categoria;
import com.academiadodesenvolvedor.marketplace.dominio.categoria.repositorios.CategoriaRepository;
import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CadastrarProdutoUseCase {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public Produto execute(CadastrarProdutoRequest request, Auth auth){
        Optional<Categoria> categoria = this.categoriaRepository.findById(request.categoriaId());
        if(categoria.isEmpty()){
            throw new NotFoundException("Categoria não encontrada!");
        }

        Produto produto = new Produto(
                auth.id(),
                request.nome(),
                categoria.get().getId(),
                request.descricao(),
                request.foto(),
                request.preco()
        );

        this.repository.save(produto);

        return produto;
    }
}
