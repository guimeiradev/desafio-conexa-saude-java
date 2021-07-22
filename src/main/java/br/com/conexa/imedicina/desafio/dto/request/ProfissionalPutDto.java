package br.com.conexa.imedicina.desafio.dto.request;

import br.com.conexa.imedicina.desafio.enumerable.AvailabilityStatus;
import lombok.Data;

@Data
public class ProfissionalPutDto {
    private Long id;
    private String fullName;
    private AvailabilityStatus status;
    private String crm;
}