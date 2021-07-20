package br.com.conexa.imedicina.desafio.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String cpf;
    private String username;
    private String passoword;
    private Integer totalAppointment;
    private String  onlineStatus;
}
