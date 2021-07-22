package br.com.conexa.imedicina.desafio.mapper;

import br.com.conexa.imedicina.desafio.domain.Profissional;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPostDto;
import br.com.conexa.imedicina.desafio.dto.request.ProfissionalPutDto;
import br.com.conexa.imedicina.desafio.dto.response.ProfissionalDto;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class ProfissionalMapper {
    public static ProfissionalDto toDto(Profissional profissional) {

        return ProfissionalDto.builder()
                .id(profissional.getId())
                .fullName(profissional.getFullName())
                .crm(profissional.getCrm())
                .status(profissional.getStatus())
                .convenios(profissional.getConvenios().stream().map(ConvenioMapper::toDto).collect(Collectors.toSet()))
                .build();
    }

    public static Profissional toEntity(ProfissionalDto profissionalDto) {
        return Profissional.builder()
                .id(profissionalDto.getId())
                .fullName(profissionalDto.getFullName())
                .status(profissionalDto.getStatus())
                .crm(profissionalDto.getCrm())
                .build();
    }

    public static Profissional toEntity(ProfissionalPostDto profissionalPostDto) {
        return Profissional.builder()
                .fullName(profissionalPostDto.getFullName())
                .crm(profissionalPostDto.getCrm())
                .avaliacao(profissionalPostDto.getAvaliacao())
                .build();
    }

    public static Profissional toEntity(ProfissionalPutDto profissionalPutDto) {
        return Profissional.builder()
                .id(profissionalPutDto.getId())
                .fullName(profissionalPutDto.getFullName())
                .status(profissionalPutDto.getStatus())
                .crm(profissionalPutDto.getCrm())
                .build();
    }
}