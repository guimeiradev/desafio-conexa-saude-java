package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class ScheduleRequestDto {

    private ZonedDateTime scheduleDateTime;
    private Long pacienteId;
    private Long profissionalId;

}
