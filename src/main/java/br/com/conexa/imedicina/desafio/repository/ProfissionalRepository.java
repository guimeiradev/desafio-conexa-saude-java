package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long>, JpaSpecificationExecutor<Profissional> {

    Set<Profissional> findAllByConvenios(Convenio convenio);

}
