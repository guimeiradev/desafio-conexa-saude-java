package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Appointment;
import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import br.com.conexa.imedicina.desafio.dto.request.ScheduleRequestDto;
import br.com.conexa.imedicina.desafio.dto.response.AvailableAppointmentDto;
import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import br.com.conexa.imedicina.desafio.exception.CustomException;
import br.com.conexa.imedicina.desafio.exception.ProfessionalAlreadyInUseException;
import br.com.conexa.imedicina.desafio.mapper.ProfissionalMapper;
import br.com.conexa.imedicina.desafio.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ConvenioService convenioService;
    private final ProfissionalService profissionalService;
    private final PacienteService pacienteService;

    public Appointment findById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Consulta não existente!", HttpStatus.NOT_FOUND));
    }

    public Set<Appointment> findAllByIds(Set<Integer> ids) {
        Set<Appointment> appointments = new HashSet<>(appointmentRepository.findAllById(ids));
        if (appointments.isEmpty()) {
            throw new CustomException("Consultas inválidos!", HttpStatus.NOT_FOUND);
        }
        return appointments;
    }

    @Transactional
    public void schedule(ScheduleRequestDto scheduleRequestDto) {
        Paciente paciente = pacienteService.findById(scheduleRequestDto.getPacienteId());

        Profissional profissional = profissionalService.findById(scheduleRequestDto.getProfissionalId());

        Appointment appointment = appointmentRepository.findByProfissionalAndSchedule(
                profissional,
                scheduleRequestDto.getScheduleDateTime()
        );

        if (Objects.nonNull(appointment)) {
            throw new ProfessionalAlreadyInUseException();
        }

        appointment = Appointment.builder()
                .paciente(paciente)
                .profissional(profissional)
                .schedule(scheduleRequestDto.getScheduleDateTime())
                .build();

        paciente.setTotalAppointment(paciente.getTotalAppointment() + 1);

        this.appointmentRepository.save(appointment);
    }

    public AvailableAppointmentDto findAvailableBySchedule(Date schedule, Long pacienteId) {
        Paciente paciente = pacienteService.findById(pacienteId);

        if (paciente.getOnlineAccessStatus().equals(AccessStatus.OFFLINE))
            throw new CustomException("Paciente offline não pode agendar", HttpStatus.BAD_REQUEST);

        Set<Profissional> profissionals = profissionalService.findAllAvailableByConvenio(paciente.getConvenio().getId());

        Set<Profissional> profissionalsUnavailable = appointmentRepository
                .findAllByProfissionalInAndSchedule(profissionals, schedule)
                .stream().map(Appointment::getProfissional).collect(Collectors.toSet());
        profissionals.removeAll(profissionalsUnavailable);

        return AvailableAppointmentDto.builder()
                .profissionals(profissionals.stream().map(ProfissionalMapper::toDto).collect(Collectors.toSet()))
                .schedule(schedule)
                .build();
    }

    public Set<Appointment> findAllAppointmentByUser(String username) {
        Paciente paciente = pacienteService.findByUsername(username);

        return appointmentRepository.findAllByPaciente(paciente);
    }

}
