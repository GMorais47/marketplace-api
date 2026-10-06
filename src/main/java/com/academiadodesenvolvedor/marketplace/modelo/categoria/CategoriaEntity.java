package com.academiadodesenvolvedor.marketplace.modelo.categoria;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tb_categorias")
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEntity {

    @Id
    public UUID id;

    @Column(nullable = false, length = 150)
    public String nome;

    @Column(columnDefinition = "TEXT", unique = true)
    public String path;

    @Column
    public boolean isDestaque;
}
