package br.com.conexa.imedicina.desafio.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
public class ScheduleRequestDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm")
    private Date scheduleDateTime;
    private Long pacienteId;
    private Long profissionalId;

}
