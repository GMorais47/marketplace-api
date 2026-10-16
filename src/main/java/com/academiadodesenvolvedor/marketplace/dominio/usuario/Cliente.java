package com.academiadodesenvolvedor.marketplace.dominio.usuario;

import com.academiadodesenvolvedor.marketplace.dominio.shared.Endereco;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.enums.Perfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class Cliente extends Usuario {

    @NonNull
    private String documento;

    @Nullable
    private LocalDate dataNascimento;

    @Nullable
    private String telefone;

    List<Endereco> enderecos;

    public Cliente(
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha,
            @NonNull String documento,
            @Nullable LocalDate dataNascimento,
            @Nullable String telefone
    ) {
        super(Perfil.CLIENTE, nome, email, senha);

        this.setDocumento(documento);
        this.setDataNascimento(dataNascimento);
        this.setTelefone(telefone);
        this.enderecos = new ArrayList<>();
    }

    private Cliente(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha,
            @NonNull String documento,
            @Nullable LocalDate dataNascimento,
            @Nullable String telefone,
            List<Endereco> enderecos
    ) {
        super(id,perfil, nome, email, senha);
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    public Cliente restaurar(
            @NonNull UUID id,
            @NonNull Perfil perfil,
            @NonNull String nome,
            @NonNull String email,
            @NonNull String senha,
            @NonNull String documento,
            @Nullable LocalDate dataNascimento,
            @Nullable String telefone,
            List<Endereco> enderecos
    ) {
        return new Cliente(
                id,
                perfil,
                nome,
                email,
                senha,
                documento,
                dataNascimento,
                telefone,
                enderecos
        );
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public void adicionarEndereco(@NonNull Endereco endereco){
        enderecos.add(endereco);
    }

    public void removerEndereco(@NonNull Endereco endereco){
        enderecos.remove(endereco);
    }
}
