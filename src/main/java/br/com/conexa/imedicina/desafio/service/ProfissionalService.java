package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPostDto;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPutDto;
import br.com.conexa.imedicina.desafio.dto.response.ProfissionalDto;
import br.com.conexa.imedicina.desafio.enumerable.AvailabilityStatus;
import br.com.conexa.imedicina.desafio.exception.CustomException;
import br.com.conexa.imedicina.desafio.mapper.ProfissionalMapper;
import br.com.conexa.imedicina.desafio.repository.ProfissionalRepository;
import br.com.conexa.imedicina.desafio.repository.ProfissionalSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ConvenioService convenioService;

    public List<ProfissionalDto> listAll() {
        return profissionalRepository.findAll().stream().map(ProfissionalMapper::toDto).collect(Collectors.toList());

    }

    public Profissional findById(long id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new CustomException("Profissional não encontrado", HttpStatus.NOT_FOUND));
    }

    public ProfissionalDto save(ProfissionalPostDto profissionalPostDto) {
        Profissional profissional = ProfissionalMapper.toEntity(profissionalPostDto);
        profissional.setStatus(AvailabilityStatus.DISPONIVEL);

        profissional.setConvenios(convenioService.findAllByIds(profissionalPostDto.getConveniosIds()));
        return ProfissionalMapper.toDto(profissionalRepository.save(profissional));
    }

    public void delete(long id) {
        profissionalRepository.delete(findById(id));
    }

    public void replace(ProfissionalPutDto profissionalPutDto) {
        Profissional saveProfissional = findById(profissionalPutDto.getId());
        Profissional profissional = ProfissionalMapper.toEntity(profissionalPutDto);
        profissional.setId(saveProfissional.getId());
        profissionalRepository.save(profissional);
    }

    public Set<Profissional> findAllAvailableByConvenio(Convenio convenio) {

        Set<Profissional> profissionais = profissionalRepository.findAllByConvenios(convenio);
        if (profissionais.isEmpty()) {
            throw new CustomException("Não existem profissionais para esse convênio", HttpStatus.NOT_FOUND);
        }
        return profissionais;
    }

    public Profissional findByNameAndCrm(String profissionalName, String profissionalCRM) {
        List<Profissional> profissionals = profissionalRepository.findAll(
                ProfissionalSpecification.byCRM(profissionalCRM)
                        .and(ProfissionalSpecification.byFullName(profissionalName)));

        if (!profissionals.isEmpty())
            return profissionals.get(0);
        return new Profissional();
    }
}