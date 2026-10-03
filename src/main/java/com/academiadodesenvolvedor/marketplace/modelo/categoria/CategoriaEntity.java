package com.academiadodesenvolvedor.marketplace.modelo.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_categorias")
@NoArgsConstructor
public class CategoriaEntity {

    @Id
    UUID id;

    @Column(nullable = false, length = 150)
    String nome;

    @Column(columnDefinition = "TEXT", unique = true)
    String path;

    @Column
    boolean isDestaque;
}
