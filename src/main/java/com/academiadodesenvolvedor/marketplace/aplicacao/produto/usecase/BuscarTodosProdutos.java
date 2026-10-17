package com.academiadodesenvolvedor.marketplace.aplicacao.produto.usecase;

import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuscarTodosProdutos {

    private final ProdutoRepository repository;

    public List<Produto> Execute(){
        return this.repository.find();
    }
}
