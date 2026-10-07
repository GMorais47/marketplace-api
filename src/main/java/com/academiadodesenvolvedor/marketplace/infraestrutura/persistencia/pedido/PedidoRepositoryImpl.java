package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido;

import com.academiadodesenvolvedor.marketplace.dominio.pedido.Pedido;
import com.academiadodesenvolvedor.marketplace.dominio.pedido.ProdutoPedido;
import com.academiadodesenvolvedor.marketplace.dominio.pedido.repositorio.PedidoRepository;
import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade.PedidoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.entidade.ProdutoPedidoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.pedido.repositorio.PedidoEntityRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.produto.ProdutoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared.EnderecoEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.ClienteEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.entidade.VendedorEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.ClienteEntityRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.usuario.repositorio.VendedorEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private final PedidoEntityRepository jpaRepository;
    private final VendedorEntityRepository vendedorEntityRepository;
    private final ClienteEntityRepository clienteEntityRepository;

    private Pedido forDomain(PedidoEntity entity) {
        EnderecoEntity enderecoEntity = entity.endereco;

        Endereco endereco = new Endereco(
                enderecoEntity.cep,
                enderecoEntity.rua,
                enderecoEntity.numero,
                enderecoEntity.bairro,
                enderecoEntity.complemento,
                enderecoEntity.cidade,
                enderecoEntity.estado
        );

        return new Pedido()
                .restaurar(
                        entity.id,
                        entity.vendedor.id,
                        entity.cliente.id,
                        endereco,
                        entity.valorTotal,
                        entity.status
                );
    }

    @Override
    public List<Pedido> findByVendedorID(UUID vendedorID) {
        List<PedidoEntity> entities = this.jpaRepository.findByVendedorID(vendedorID);
        return entities
                .stream()
                .map(entity -> this.forDomain(entity))
                .toList();
    }

    @Override
    public List<Pedido> findByClienteID(UUID clienteID) {
        List<PedidoEntity> entities = this.jpaRepository.findByClienteID(clienteID);
        return entities
                .stream()
                .map(entity -> this.forDomain(entity))
                .toList();
    }

    @Override
    public void save(Pedido pedido) {

        Optional<VendedorEntity> vendedorEntity = this.vendedorEntityRepository.findById(pedido.getVendedorID());
        if (vendedorEntity.isEmpty()) throw new EntityNotFoundException("Vendedor não encontrado");

        Optional<ClienteEntity> clienteEntity = this.clienteEntityRepository.findById(pedido.getClienteID());
        if (clienteEntity.isEmpty()) throw new EntityNotFoundException("Cliente não encontrado");

        Endereco endereco = pedido.getEndereco();

        EnderecoEntity enderecoEntity = new EnderecoEntity(
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getCidade(),
                endereco.getEstado()
        );

        PedidoEntity entity = new PedidoEntity(
                pedido.getId(),
                vendedorEntity.get(),
                clienteEntity.get(),
                enderecoEntity,
                pedido.getValorTotal(),
                pedido.getStatus(),
                new ArrayList<>()
        );

        List<ProdutoPedidoEntity> produtosPedidos = pedido.getProdutos()
                .stream()
                .map(
                        produtoPedido -> {

                            ProdutoEntity produtoEntity = this.entityManager.getReference(
                                    ProdutoEntity.class,
                                    produtoPedido.getPedidoID()
                            );

                            return new ProdutoPedidoEntity(
                                    produtoPedido.getId(),
                                    entity,
                                    produtoEntity,
                                    produtoPedido.getQuantidade(),
                                    produtoPedido.getPrecoUnitario(),
                                    produtoPedido.getValorTotal()
                            );
                        }
                )
                .toList();

        entity.produtos.addAll(produtosPedidos);
        this.jpaRepository.save(entity);
    }
}
