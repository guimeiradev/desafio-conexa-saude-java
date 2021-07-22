package br.com.conexa.imedicina.desafio.mapper;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class PacienteMapper {

    public static PacienteDto toDto(Paciente pacientes) {

        return PacienteDto.builder().id(pacientes.getId())
                .fullName(pacientes.getFullName())
                .cpf(pacientes.getCpf())
                .totalAppointment(pacientes.getTotalAppointment())
                .onlineAccessStatus(pacientes.getOnlineAccessStatus())
                .build();
    }


    public static Paciente toEntity(PacienteDto pacientePostDto) {
        return Paciente.builder()
                .id(pacientePostDto.getId())
                .fullName(pacientePostDto.getFullName())
                .cpf(pacientePostDto.getCpf())
                .totalAppointment(pacientePostDto.getTotalAppointment())
                .onlineAccessStatus(pacientePostDto.getOnlineAccessStatus())
                .build();
    }

    public static Paciente toEntity(PacientePostDto pacientePostDto) {
        return Paciente.builder()
                .fullName(pacientePostDto.getFullName())
                .cpf(pacientePostDto.getCpf())
                .username(pacientePostDto.getUsername())
                .password(pacientePostDto.getPassword())
                .build();
    }

}
