package br.com.conexa.imedicina.desafio.controller;


import br.com.conexa.imedicina.desafio.dto.request.AvailableAppoitmentRequestDto;
import br.com.conexa.imedicina.desafio.dto.request.ScheduleRequestDto;
import br.com.conexa.imedicina.desafio.dto.response.AppointmentDto;
import br.com.conexa.imedicina.desafio.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
@RequiredArgsConstructor
public class ConsultaController {

    private final AppointmentService appointmentService;

    @PostMapping("agendar")
    public ResponseEntity<Void> scheduleAppointment(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        appointmentService.schedule(scheduleRequestDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("agendas-disponiveis/{pacienteId}")
    public ResponseEntity<AppointmentDto> findAvailableSchedules(@RequestBody AvailableAppoitmentRequestDto availableAppoitmentRequestDto) {
        return ResponseEntity.ok(appointmentService.findAvailableBySchedule(
                availableAppoitmentRequestDto.getScheduleDateTime(),
                availableAppoitmentRequestDto.getPacienteId()));
    }

}
