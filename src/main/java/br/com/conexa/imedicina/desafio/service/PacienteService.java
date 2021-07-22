package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePutDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteDto;
import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import br.com.conexa.imedicina.desafio.exception.CustomException;
import br.com.conexa.imedicina.desafio.mapper.PacienteMapper;
import br.com.conexa.imedicina.desafio.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ConvenioService convenioService;

    public List<PacienteDto> listAll() {
        return pacienteRepository.findAll().stream().map(PacienteMapper::toDto).collect(Collectors.toList());

    }

    public Paciente findById(long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new CustomException("Paciente não encontrado", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Paciente save(PacientePostDto pacientePostDto) {
        Paciente paciente = PacienteMapper.toEntity(pacientePostDto);

        paciente.setOnlineAccessStatus(AccessStatus.OFFLINE);
        paciente.setTotalAppointment(0);
        paciente.setConvenio(convenioService.findById(pacientePostDto.getConvenioId()));

        return pacienteRepository.save(paciente);
    }

    @Transactional
    public void delete(long id) {
        pacienteRepository.delete(findById(id));
    }

    @Transactional
    public Paciente update(long id, PacientePutDto pacientePutDto) {
        Paciente savePaciente = findById(id);
        savePaciente.setFullName(pacientePutDto.getFullName());
        return pacienteRepository.save(savePaciente);
    }

}
