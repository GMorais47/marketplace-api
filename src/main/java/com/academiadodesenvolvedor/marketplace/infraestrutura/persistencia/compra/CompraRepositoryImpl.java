package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.NotFoundException;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Compra;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.ProdutoPedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.repositorio.CompraRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.CompraEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.PedidoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.ProdutoPedidoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.repositorio.CompraEntityRepository;

import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto.ProdutoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto.ProdutoEntityRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.ClienteEntityRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.VendedorEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class CompraRepositoryImpl implements CompraRepository {

    private CompraEntityRepository repository;
    private ClienteEntityRepository clienteEntityRepository;
    private VendedorEntityRepository vendedorEntityRepository;
    private ProdutoEntityRepository produtoEntityRepository;

    @Override
    public void save(Compra compra) {
        ClienteEntity cliente = this.clienteEntityRepository.findById(compra.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));

        List<PedidoEntity> pedidos = new ArrayList<>();

        CompraEntity entity = CompraEntity.fromDomain(
                compra,
                cliente,
                pedidos
        );

        for (Pedido pedido : compra.getPedidos()) {
            VendedorEntity vendedor = this.vendedorEntityRepository.findById(pedido.getVendedorID())
                    .orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));

            List<ProdutoPedidoEntity> produtoPedidoEntities = new ArrayList<>();

            PedidoEntity pedidoEntity = PedidoEntity.fromDomain(
                    pedido,
                    entity,
                    vendedor,
                    produtoPedidoEntities
            );

            for (ProdutoPedido produtoPedido : pedido.getProdutos()) {
                ProdutoEntity produtoEntity = this.produtoEntityRepository.findById(produtoPedido.getProdutoID())
                        .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

                ProdutoPedidoEntity produtoPedidoEntity = ProdutoPedidoEntity.fromDomain(
                        produtoPedido,
                        pedidoEntity,
                        produtoEntity
                );

                produtoPedidoEntities.add(produtoPedidoEntity);
            }

            pedidoEntity.setProdutos(produtoPedidoEntities);
            pedidos.add(pedidoEntity);
        }
        entity.setPedidos(pedidos);

        this.repository.save(entity);
    }
}
