package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.domain.Paciente;
import br.com.conexa.imedicina.desafio.enumerable.AccessStatus;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.Set;

@UtilityClass
public class PacienteSpecification {

    public static Specification<Paciente> byFullName(String name) {
        return (root, query, builder) -> {
            if (Strings.isNotBlank(name))
                return builder.equal(root.get("fullName"), name);
            return null;
        };
    }

    public static Specification<Paciente> byStatus(AccessStatus status) {
        return (root, query, builder) -> {
            if (Objects.nonNull(status))
                return builder.equal(root.get("onlineAccessStatus"), status);
            return null;
        };
    }

    public static Specification<Paciente> byConvenioIn(Set<Convenio> convenios) {
        return (root, query, builder) -> {
            if (convenios != null && !convenios.isEmpty())
                return root.get("convenio").in(convenios);
            return null;
        };
    }

}
