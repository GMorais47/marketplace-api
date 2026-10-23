package com.academiadodesenvolvedor.marketplace.dominio.compra;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class Compra {

    @NonNull
    private final UUID id;

    @NonNull
    private final UUID clienteId;

    @NonNull
    @Setter
    private Endereco endereco;

    @NonNull
    private List<Pedido> pedidos;

    @NonNull
    private BigDecimal total;

    public Compra(
            @NonNull UUID clienteId,
            @NonNull Endereco endereco,
            @NonNull List<Pedido> pedidos
    ) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.endereco = endereco;
        this.setPedidos(pedidos);
    }

    public void calcularTotal() {
        this.total = this.pedidos.stream()
                .map(Pedido::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setPedidos(List<Pedido> pedidos){
        this.pedidos = pedidos;
        this.calcularTotal();
    }
}
