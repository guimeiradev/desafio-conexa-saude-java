package br.com.conexa.imedicina.desafio.domain;

import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "totalAppointment", nullable = false)
    private Integer totalAppointment;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "onlineStatus", nullable = false)
    private AccessStatus onlineAccessStatus;

    @ManyToOne
    private Convenio convenio;
}
