package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ProfissionalPostDto {
    private String fullName;
    private String crm;
    private Integer avaliacao;
    private Set<Integer> conveniosIds;
}