package com.academiadodesenvolvedor.marketplace.aplicacao.usuario.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.exception.NotFoundException;
import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.ClienteRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ListarEnderecosClienteUseCase {

    private ClienteRepository clienteRepository;

    public List<Endereco> execute(UUID id){
        return this.clienteRepository.findEnderecoById(id);
    }
}
