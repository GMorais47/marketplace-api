package com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra;

import com.academiadodesenvolvedor.marketplace.dominio.compra.Compra;
import com.academiadodesenvolvedor.marketplace.dominio.compra.repositorio.CompraRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.entidade.CompraEntity;
import com.academiadodesenvolvedor.marketplace.infraestrutura.persistencia.compra.repositorio.CompraEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CompraRepositoryImpl implements CompraRepository {

    private CompraEntityRepository repository;

    @Override
    public void save(Compra compra){
        CompraEntity entity = CompraEntity.fromDomain(compra);
        this.repository.save(entity);
    }
}
