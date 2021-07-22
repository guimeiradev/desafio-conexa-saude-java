package br.com.conexa.imedicina.desafio.controller;

import br.com.conexa.imedicina.desafio.dto.request.PacientePostDto;
import br.com.conexa.imedicina.desafio.dto.request.PacientePutDto;
import br.com.conexa.imedicina.desafio.dto.response.PacienteDto;
import br.com.conexa.imedicina.desafio.mapper.PacienteMapper;
import br.com.conexa.imedicina.desafio.service.PacienteService;
import br.com.conexa.imedicina.desafio.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<PacienteDto>> list() {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok((pacienteService.listAll()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PacienteDto> findById(@PathVariable long id) {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(PacienteMapper.toDto(pacienteService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PacienteDto> save(@RequestBody PacientePostDto pacientePostDto) {
        return new ResponseEntity<>(PacienteMapper.toDto(pacienteService.save(pacientePostDto)), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        pacienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PacienteDto> update(@PathVariable long id, @RequestBody PacientePutDto pacientePutDto) {
        return new ResponseEntity<>(PacienteMapper.toDto(pacienteService.update(id, pacientePutDto)), HttpStatus.OK);
    }
}
