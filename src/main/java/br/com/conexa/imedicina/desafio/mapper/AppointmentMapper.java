package br.com.conexa.imedicina.desafio.mapper;

import br.com.conexa.imedicina.desafio.domain.Appointment;
import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.dto.request.ConvenioDto;
import br.com.conexa.imedicina.desafio.dto.response.AppointmentDto;
import org.mapstruct.Mapper;

public class AppointmentMapper {

    public static AppointmentDto toDto(Appointment appointment) {

        return AppointmentDto.builder()
                .schedule(appointment.getSchedule())
                .profissional(ProfissionalMapper.toDto(appointment.getProfissional()))
                .build();
    }

}