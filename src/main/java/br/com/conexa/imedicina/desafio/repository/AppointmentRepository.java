package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Appointment;
import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Appointment findByProfissionalAndSchedule(
            Profissional profissional,
            Date schedule
    );

    Set<Appointment> findAllByProfissionalInAndSchedule(Set<Profissional> profissionals, Date schedule);

    Set<Appointment> findAllByPaciente(Paciente paciente);

}
