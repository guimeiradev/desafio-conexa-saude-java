package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProfissionalRepository extends JpaRepository<Profissional,Long> {

    Set<Profissional> findAllByConvenios(Convenio convenio);

}
