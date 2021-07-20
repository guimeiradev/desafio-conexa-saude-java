package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Data;

@Data
public class PacientePostDto {
    private Long id;
    private String fullName;
    private String cpf;
    private String username;
    private String passoword;
    private Integer totalAppointment;
    private String  onlineStatus;

}
