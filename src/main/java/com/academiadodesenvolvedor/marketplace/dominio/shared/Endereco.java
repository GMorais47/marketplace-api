package com.academiadodesenvolvedor.marketplace.dominio.shared;

import lombok.Getter;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;

@Getter
public class Endereco {

    @NonNull
    private String cep;

    @NonNull
    private String rua;

    @NonNull
    private String numero;

    @NonNull
    private String bairro;

    @Nullable
    private String complemento;

    @NonNull
    private String cidade;

    @NonNull
    private String estado;

    public Endereco(
            @NonNull String cep,
            @NonNull String rua,
            @NonNull String numero,
            @NonNull String bairro,
            @Nullable String complemento,
            @NonNull String cidade,
            @NonNull String estado
    ) {
        this.setCep(cep);
        this.setRua(rua);
        this.setNumero(numero);
        this.setBairro(bairro);
        this.setComplemento(complemento);
        this.setCidade(cidade);
        this.setEstado(estado);
    }

    public void setCep(@NonNull String cep) {
        this.cep = cep;
    }

    public void setRua(@NonNull String rua) {
        this.rua = rua;
    }

    public void setNumero(@NonNull String numero) {
        this.numero = numero;
    }

    public void setBairro(@NonNull String bairro) {
        this.bairro = bairro;
    }

    public void setComplemento(@Nullable String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(@NonNull String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(@NonNull String estado) {
        this.estado = estado;
    }
}
