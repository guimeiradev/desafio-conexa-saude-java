package br.com.conexa.imedicina.desafio.mapper;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePutDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteGetDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class PacienteMapper {

    public static PacienteGetDto toDto(Paciente pacientes) {

        return PacienteGetDto.builder().id(pacientes.getId())
                .fullName(pacientes.getFullName())
                .cpf(pacientes.getCpf())
                .totalAppointment(pacientes.getTotalAppointment())
                .onlineStatus(pacientes.getOnlineStatus())
                .build();
    }


    public static Paciente toEntity(PacienteGetDto pacientePostDto) {
        return Paciente.builder()
                .id(pacientePostDto.getId())
                .fullName(pacientePostDto.getFullName())
                .cpf(pacientePostDto.getCpf())
                .totalAppointment(pacientePostDto.getTotalAppointment())
                .onlineStatus(pacientePostDto.getOnlineStatus())
                .build();
    }

    public static Paciente toEntity(PacientePostDto pacientePostDto) {
        return Paciente.builder().id(pacientePostDto.getId())
                .fullName(pacientePostDto.getFullName())
                .cpf(pacientePostDto.getCpf())
                .username(pacientePostDto.getUsername())
                .passoword(pacientePostDto.getPassoword())
                .totalAppointment(pacientePostDto.getTotalAppointment())
                .onlineStatus(pacientePostDto.getOnlineStatus())
                .build();
    }
    public static Paciente toEntity(PacientePutDto pacientePutDto) {
        return Paciente.builder()
                .id(pacientePutDto.getId())
                .fullName(pacientePutDto.getFullName())
                .cpf(pacientePutDto.getCpf())
                .username(pacientePutDto.getUsername())
                .passoword(pacientePutDto.getPassoword())
                .totalAppointment(pacientePutDto.getTotalAppointment())
                .onlineStatus(pacientePutDto.getOnlineStatus())
                .build();
    }
}
