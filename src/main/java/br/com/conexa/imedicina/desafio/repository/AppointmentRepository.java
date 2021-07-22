package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Appointment;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Set;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findByProfissionalAndSchedule(
            Profissional profissional,
            ZonedDateTime schedule
    );

    Set<Appointment> findAllByProfissionalInAndSchedule(Set<Profissional> profissionals, ZonedDateTime schedule);
}
