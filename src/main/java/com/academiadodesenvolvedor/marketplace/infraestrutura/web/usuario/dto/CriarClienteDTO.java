package com.academiadodesenvolvedor.marketplace.infraestrutura.web.usuario.dto;

import com.academiadodesenvolvedor.marketplace.infraestrutura.web.shared.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CriarClienteDTO(
        @NotBlank(message = "O nome é de preenchimento obrigatório!")
        @Length(min = 3, max = 150, message = "O nome deve ter entre 3 - 150 caracteres!")
        String nome,

        @NotBlank(message = "O e-mail é de preenchimento obrigatório!")
        @Email(message = "E-mail é inválido!")
        String email,

        @NotBlank(message = "A senha é de preenchimento obrigatório!")
        @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres!")
        String senha,

        @NotBlank(message = "O documento é de preenchimento obrigatório!")
        @Length(min = 11, max = 11, message = "O documento deve ter 11 caracteres!")
        String documento,

        @NotNull(message = "A data de nascimento é de preenchimento obrigatório!")
        @Past(message = "A data de nascimento deve ser inferior a data atual!")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotBlank(message = "O telefone é de preenchimento obrigatório!")
        @Length(min = 11, max = 11, message = "O telefone deve ter 11 caracteres!")
        String telefone,

        @NotNull(message = "O endereço é de preenchimento obrigatório!")
        @Valid
        EnderecoDTO endereco
) {
}
