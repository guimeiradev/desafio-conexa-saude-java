package br.com.conexa.imedicina.desafio.dto.response;

import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDto {
    private Long id;
    private String fullName;
    private String username;
    private String cpf;
    private Integer totalAppointment;
    private AccessStatus onlineAccessStatus;
}
