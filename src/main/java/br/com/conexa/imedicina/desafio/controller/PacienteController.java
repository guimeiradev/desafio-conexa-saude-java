package br.com.conexa.imedicina.desafio.controller;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePutDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteGetDto;
import br.com.conexa.imedicina.desafio.service.PacienteService;
import br.com.conexa.imedicina.desafio.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pacientes")
@Log4j2
@RequiredArgsConstructor
public class PacienteController {

    private final DateUtil dateUtil;
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteGetDto>>list() {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok((pacienteService.listAll()));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<PacienteGetDto>findById(@PathVariable long id) {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(pacienteService.findByIdOrThrowBadRequestException(id));
    }
    @Transactional
    @PostMapping
    public ResponseEntity<Paciente>save(@RequestBody PacientePostDto pacientePostDto) {
        return new ResponseEntity<>(pacienteService.save(pacientePostDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable long id) {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody PacientePutDto pacientePutDto) {
        pacienteService.replace(pacientePutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
