package br.com.conexa.imedicina.desafio.controller;

import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPostDto;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPutDto;
import br.com.conexa.imedicina.desafio.dto.response.ProfissionalDto;
import br.com.conexa.imedicina.desafio.mapper.ProfissionalMapper;
import br.com.conexa.imedicina.desafio.service.PacienteService;
import br.com.conexa.imedicina.desafio.service.ProfissionalService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("profissionais")
@Log4j2
@RequiredArgsConstructor
public class ProfissionalController {
    private final DateUtil dateUtil;
    private final ProfissionalService profissionalService;
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<ProfissionalDto>> list() {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok((profissionalService.listAll()));
    }

    @GetMapping("paciente")
    public ResponseEntity<Set<ProfissionalDto>> findAllAvailableByPacienteUsername(@RequestParam String username) {
        return ResponseEntity.ok((
                profissionalService.findAllAvailableByPacienteUsername(pacienteService.findByUsername(username))
                        .stream()
                        .map(ProfissionalMapper::toDto)
                        .collect(Collectors.toSet())));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfissionalDto> findById(@PathVariable long id) {
        log.info((dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now())));
        return ResponseEntity.ok(ProfissionalMapper.toDto(profissionalService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ProfissionalDto> save(@RequestBody ProfissionalPostDto profissionalPostDto) {
        return new ResponseEntity<>(profissionalService.save(profissionalPostDto), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        profissionalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProfissionalPutDto profissionalPutDto) {
        profissionalService.update(profissionalPutDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}