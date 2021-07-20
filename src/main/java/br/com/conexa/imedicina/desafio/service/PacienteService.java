package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePutDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteGetDto;
import br.com.conexa.imedicina.desafio.mapper.PacienteMapper;
import br.com.conexa.imedicina.desafio.repository.PacienteRepository;
import br.com.conexa.imedicina.desafio.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public List<PacienteGetDto> listAll() {
        return pacienteRepository.findAll().stream().map(PacienteMapper :: toDto).collect(Collectors.toList());

    }

    public PacienteGetDto findByIdOrThrowBadRequestException(long id) {
        var paciente=  pacienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Paciente não encontrado"));

        return PacienteMapper.toDto(paciente);
    }

    public Paciente save(PacientePostDto pacientePostDto) {
        PacienteMapper.toEntity(pacientePostDto);
        Paciente paciente = Paciente.builder()
                .id(pacientePostDto.getId())
                .fullName(pacientePostDto.getFullName())
                .cpf(pacientePostDto.getCpf())
                .username(pacientePostDto.getUsername())
                .passoword(pacientePostDto.getPassoword())
                .totalAppointment(pacientePostDto.getTotalAppointment())
                .onlineStatus(pacientePostDto.getOnlineStatus())
                .build();

        return pacienteRepository.save(paciente);
    }

    public void delete(long id) {
        pacienteRepository.delete(PacienteMapper.toEntity(findByIdOrThrowBadRequestException(id)));
    }

    public void replace(PacientePutDto pacientePutDto) {
        Paciente savePaciente =PacienteMapper.toEntity(findByIdOrThrowBadRequestException(pacientePutDto.getId()));
        Paciente paciente = PacienteMapper.toEntity(pacientePutDto);
        paciente.setId(savePaciente.getId());
        pacienteRepository.save(paciente);
    }
}
