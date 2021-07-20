package br.com.conexa.imedicina.desafio.controller;

import br.com.conexa.imedicina.desafio.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profissionais")
@Log4j2
@RequiredArgsConstructor
public class ProfissionalController {
    private final DateUtil dateUtil;

}
