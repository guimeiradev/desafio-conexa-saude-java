package br.com.conexa.imedicina.desafio.dto.response;

import br.com.conexa.imedicina.desafio.dto.request.ConvenioDto;
import br.com.conexa.imedicina.desafio.enumerable.AvailabilityStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProfissionalDto {
    private Long id;
    private String fullName;
    private AvailabilityStatus status;
    private String crm;
    private Set<ConvenioDto> convenios;
}