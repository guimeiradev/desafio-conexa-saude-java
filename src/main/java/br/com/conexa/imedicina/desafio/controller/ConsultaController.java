package br.com.conexa.imedicina.desafio.controller;


import br.com.conexa.imedicina.desafio.dto.request.AvailableAppoitmentRequestDto;
import br.com.conexa.imedicina.desafio.dto.request.ScheduleRequestDto;
import br.com.conexa.imedicina.desafio.dto.response.AppointmentDto;
import br.com.conexa.imedicina.desafio.dto.response.AvailableAppointmentDto;
import br.com.conexa.imedicina.desafio.mapper.AppointmentMapper;
import br.com.conexa.imedicina.desafio.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Set<AppointmentDto>> findAllAppointmentByUser(@RequestParam String username) {
        return ResponseEntity.ok(
                appointmentService.findAllAppointmentByUser(username)
                        .stream().map(AppointmentMapper::toDto).collect(Collectors.toSet()));
    }

    @PostMapping("agendar")
    public ResponseEntity<Void> scheduleAppointment(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        appointmentService.schedule(scheduleRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("agendas-disponiveis/{pacienteId}")
    public ResponseEntity<AvailableAppointmentDto> findAvailableSchedules(@RequestBody AvailableAppoitmentRequestDto availableAppoitmentRequestDto) {
        return ResponseEntity.ok(appointmentService.findAvailableBySchedule(
                availableAppoitmentRequestDto.getScheduleDateTime(),
                availableAppoitmentRequestDto.getPacienteId()));
    }

}
