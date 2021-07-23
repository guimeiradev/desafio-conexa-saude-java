package br.com.conexa.imedicina.desafio.repository;

import br.com.conexa.imedicina.desafio.domain.Profissional;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class ProfissionalSpecification {

    public static Specification<Profissional> byFullName(String name) {
        return (root, query, builder) -> {
            if (Strings.isNotBlank(name))
                return builder.equal(root.get("fullName"), name);
            return null;
        };
    }

    public static Specification<Profissional> byCRM(String crm) {
        return (root, query, builder) -> {
            if (Strings.isNotBlank(crm))
                return builder.equal(root.get("crm"), crm);
            return null;
        };
    }

}
