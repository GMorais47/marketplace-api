package com.academiadodesenvolvedor.marketplace.dominio.pedido;

import com.academiadodesenvolvedor.marketplace.dominio.pedido.enums.PedidoStatus;
import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Pedido {

    @NonNull
    private UUID id;

    @NonNull
    private UUID vendedorID;

    @NonNull
    private UUID clienteID;

    @NonNull
    private Endereco endereco;

    @NonNull
    private BigDecimal valorTotal;

    @NonNull
    private PedidoStatus status;

    @NonNull
    private List<ProdutoPedido> produtos;

    public Pedido(
            @NonNull UUID vendedorID,
            @NonNull UUID clienteID,
            @NonNull Endereco endereco,
            @NonNull List<ProdutoPedido> produtos
    ) {
        this.id = UUID.randomUUID();
        this.vendedorID = vendedorID;
        this.clienteID = clienteID;
        this.setEndereco(endereco);
        this.setProdutos(produtos);
        this.status = PedidoStatus.PENDENTE;
    }

    private Pedido(
            @NonNull UUID id,
            @NonNull UUID vendedorID,
            @NonNull UUID clienteID,
            @NonNull Endereco endereco,
            @NonNull BigDecimal valorTotal,
            @NonNull PedidoStatus status
    ) {
        this.id = id;
        this.vendedorID = vendedorID;
        this.clienteID = clienteID;
        this.endereco = endereco;
        this.valorTotal = valorTotal;
        this.status = status;
        this.produtos = new ArrayList<>();
    }

    public Pedido restaurar(
            @NonNull UUID id,
            @NonNull UUID vendedorID,
            @NonNull UUID clienteID,
            @NonNull Endereco endereco,
            @NonNull BigDecimal valorTotal,
            @NonNull PedidoStatus status
    ){
        return new Pedido(
                id,
                vendedorID,
                clienteID,
                endereco,
                valorTotal,
                status
        );
    }

    private void validarStatus(){
        if(this.status.equals(PedidoStatus.APROVADO)) throw new IllegalArgumentException("Não é possivel alterar um pedido finalizado");
    }

    private void calcularValorTotal(){
        this.valorTotal = produtos.stream()
                .map(ProdutoPedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setEndereco(@NonNull Endereco endereco) {
        this.validarStatus();
        this.endereco = endereco;
    }

    public void setProdutos(@NonNull List<ProdutoPedido> produtos) {
        this.validarStatus();
        this.produtos = produtos;
        this.calcularValorTotal();
    }

    public void aprovar(){
        this.validarStatus();
        this.status = PedidoStatus.APROVADO;
    }

    public void recusar(){
        this.validarStatus();
        this.status = PedidoStatus.RECUSADO;
    }

    public void autorizar(){
        this.validarStatus();
        this.status = PedidoStatus.AUTORIZADO;
    }

    public void emAnalise(){
        this.validarStatus();
        this.status = PedidoStatus.EM_ANALISE;
    }
}
