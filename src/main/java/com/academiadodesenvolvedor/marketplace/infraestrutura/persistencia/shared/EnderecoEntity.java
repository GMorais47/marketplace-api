package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {
    @Column(nullable = false)
    public String cep;

    @Column(nullable = false)
    public String rua;

    @Column(nullable = false)
    public String numero;

    @Column(nullable = false)
    public String bairro;

    @Column
    public String complemento;

    @Column(nullable = false)
    public String cidade;

    @Column(nullable = false)
    public String estado;
}
