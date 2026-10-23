package com.academiadodesenvolvedor.marketplace.dominio.compra;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
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
    private UUID compraId;

    @NonNull
    private List<ProdutoPedido> produtos;

    @NonNull
    private BigDecimal total;

    public Pedido(
            @NonNull UUID vendedorID,
            @NonNull UUID compraId,
            @NonNull List<ProdutoPedido> produtos
    ) {
        this.id = UUID.randomUUID();
        this.vendedorID = vendedorID;
        this.compraId = compraId;
        this.setProdutos(produtos);
    }

    private void calcularTotal(){
        this.total = produtos.stream()
                .map(ProdutoPedido::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setProdutos(@NonNull List<ProdutoPedido> produtos) {
        this.produtos = produtos;
        this.calcularTotal();
    }

}
