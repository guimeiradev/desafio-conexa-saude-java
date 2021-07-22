package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvenioRespository extends JpaRepository<Convenio, Integer> {

    List<Convenio> findByName(String name);

}
