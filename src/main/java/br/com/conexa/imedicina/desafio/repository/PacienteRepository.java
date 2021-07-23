package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PacienteRepository extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

    Paciente findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Paciente p SET p.onlineAccessStatus = 0 WHERE p.id = :id")
    void setPacienteOnlineById(Long id);

//    List<Cliente>findByName(String name);
}
