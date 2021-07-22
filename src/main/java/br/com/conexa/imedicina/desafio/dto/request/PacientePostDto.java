package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Data;

@Data
public class PacientePostDto {
    private String fullName;
    private String cpf;
    private String username;
    private String password;
    private Integer convenioId;
}
