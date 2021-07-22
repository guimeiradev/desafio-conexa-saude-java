package br.com.conexa.imedicina.desafio.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Builder
public class AppointmentDto {

    private ZonedDateTime schedule;

    private Set<ProfissionalDto> profissionals;

}
