package br.com.conexa.imedicina.desafio.mapper;

import br.com.conexa.imedicina.desafio.domain.Convenio;
import br.com.conexa.imedicina.desafio.dto.request.ConvenioDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ConvenioMapper {

    public static ConvenioDto toDto(Convenio convenio) {

        return ConvenioDto.builder()
                .id(convenio.getId())
                .name(convenio.getName())
                .build();
    }

    public static Convenio toEntity(ConvenioDto convenioDto) {
        return Convenio.builder()
                .id(convenioDto.getId())
                .name(convenioDto.getName())
                .build();
    }
}