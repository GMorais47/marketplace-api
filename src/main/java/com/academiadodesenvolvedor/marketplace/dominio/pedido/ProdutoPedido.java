package com.academiadodesenvolvedor.marketplace.dominio.pedido;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProdutoPedido {

    @NonNull
    private final UUID id;

    @NonNull
    private final UUID pedidoID;

    @NonNull
    private final UUID produtoID;

    @NonNull
    private Integer quantidade;

    @NonNull
    private BigDecimal precoUnitario;

    @NonNull
    private BigDecimal valorTotal;

    public ProdutoPedido(
            @NonNull UUID pedidoID,
            @NonNull UUID produtoID,
            @NonNull Integer quantidade,
            @NonNull BigDecimal precoUnitario
    ) {
        this.id = UUID.randomUUID();
        this.pedidoID = pedidoID;
        this.produtoID = produtoID;
        this.setQuantidade(quantidade);
        this.setPrecoUnitario(precoUnitario);
    }

    private void calcularValorTotal(){
        this.valorTotal = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade.longValue()));
    }

    public void setQuantidade(@NonNull Integer quantidade) {
        this.quantidade = quantidade;
        this.calcularValorTotal();
    }

    public void setPrecoUnitario(@NonNull BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.calcularValorTotal();
    }
}
