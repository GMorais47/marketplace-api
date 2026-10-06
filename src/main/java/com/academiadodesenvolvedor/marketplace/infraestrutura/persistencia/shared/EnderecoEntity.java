package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.shared;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoEntity {
    @Column(nullable = false)
    String cep;

    @Column(nullable = false)
    String rua;

    @Column(nullable = false)
    String numero;

    @Column(nullable = false)
    String bairro;

    @Column
    String complemento;

    @Column(nullable = false)
    String cidade;

    @Column(nullable = false)
    String estado;
}
