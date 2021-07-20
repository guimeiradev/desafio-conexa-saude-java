package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Profissional;
import br.com.conexa.imedicina.desafio.repository.ProfissionalRepository;
import br.com.conexa.imedicina.desafio.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfissionalService {
    private final ProfissionalRepository profissionalRepository;

    public List<Profissional> listAll() {
        return profissionalRepository.findAll();
    }

    public Profissional findByIdOrThrowBadRequestException(long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Profissional não encontrado"));
    }

}
