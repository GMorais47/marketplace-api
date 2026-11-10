package com.academiadodesenvolvedor.marketplace.dominio.compra;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

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

    @NonNull
    private Integer parcelas;

    @Nullable
    @Setter
    private String pagamentoId;

    @NonNull
    @Setter
    private String status;

    public Compra(
            @NonNull UUID clienteId,
            @NonNull Endereco endereco,
            @NonNull List<Pedido> pedidos,
            @NonNull Integer parcelas
    ) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.endereco = endereco;
        this.setPedidos(pedidos);
        this.setParcelas(parcelas);
        this.status = "pending";
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

    public void setParcelas(Integer parcelas){
        this.parcelas = parcelas < 1 ? 1 : parcelas;
    }
}
