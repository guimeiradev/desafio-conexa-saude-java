package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class AvailableAppoitmentRequestDto {

    private ZonedDateTime scheduleDateTime;
    private Long pacienteId;

}
