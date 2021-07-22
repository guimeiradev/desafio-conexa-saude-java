package br.com.conexa.imedicina.desafio.service;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.exception.CustomException;
import br.com.conexa.imedicina.desafio.repository.ConvenioRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConvenioService {

    private final ConvenioRespository convenioRespository;

    public Convenio findById(Integer id) {
        return convenioRespository.findById(id)
                .orElseThrow(() -> new CustomException("Convênio não existente!", HttpStatus.NOT_FOUND));
    }

    public Set<Convenio> findAllByIds(Set<Integer> ids) {
        Set<Convenio> convenios = new HashSet<>(convenioRespository.findAllById(ids));
        if (convenios.isEmpty()) {
            throw new CustomException("Convênios inválidos!", HttpStatus.NOT_FOUND);
        }
        return convenios;
    }
}
