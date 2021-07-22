package br.com.conexa.imedicina.desafio.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConvenioDto {

    private Integer id;
    private String name;

}
