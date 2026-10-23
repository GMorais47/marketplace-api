package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.CadastrarCompraRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.CompraProdutoRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.exception.NotFoundException;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Compra;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.ProdutoPedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.repositorio.CompraRepository;
import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CadastrarCompraUseCase {

    private CompraRepository repository;
    private ProdutoRepository produtoRepository;

    @Transactional
    public void execute(CadastrarCompraRequest request, Auth auth) {
        Compra compra = new Compra(
                auth.id(),
                request.endereco(),
                new ArrayList<>()
        );

        Set<UUID> prdUnique = new HashSet<>(
                request.produtos().stream()
                        .map(CompraProdutoRequest::produtoId).toList()
        );

        List<Produto> produtosDaCompra = this.produtoRepository.findAllById(prdUnique);

        if (produtosDaCompra.size() != prdUnique.size()) {
            throw new NotFoundException("Um dos produtos informados não foi encontrado!");
        }

        Set<UUID> vendUnique = new HashSet<>(
                produtosDaCompra.stream()
                        .map(Produto::getVendedorID).toList()
        );

        List<Pedido> pedidos = new ArrayList<>();

        for (UUID vendedor : vendUnique) {
            List<Produto> produtos = produtosDaCompra.stream().filter(
                    produto -> produto.getVendedorID().equals(vendedor)
            ).toList();

            Pedido pedido = new Pedido(vendedor, compra.getId(), new ArrayList<>());

            List<ProdutoPedido> produtoPedidos = new ArrayList<>();

            for (Produto produto : produtos) {
                CompraProdutoRequest prdCompra = request.produtos()
                        .stream()
                        .filter(p -> p.produtoId().equals(produto.getId()))
                        .findFirst()
                        .orElseThrow(() -> new NotFoundException("Produto não encontrado no Pedido!"));

                ProdutoPedido produtoPedido = new ProdutoPedido(pedido.getId(), produto.getId(), prdCompra.quantidade(), prdCompra.valor());
                produtoPedidos.add(produtoPedido);
            }

            pedido.setProdutos(produtoPedidos);

            pedidos.add(pedido);
        }

        compra.setPedidos(pedidos);

        this.repository.save(compra);
    }
}
