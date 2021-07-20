package br.com.conexa.imedicina.desafio.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteGetDto {
    private Long id;
    private String fullName;
    private String cpf;
    private Integer totalAppointment;
    private String  onlineStatus;
}
