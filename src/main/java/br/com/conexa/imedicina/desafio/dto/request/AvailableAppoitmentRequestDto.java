package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;

@Data
@Builder
public class AvailableAppoitmentRequestDto {

    private Date scheduleDateTime;
    private Long pacienteId;

}
