package com.code.controlecandidatosapi.candidatos.mapper;

import com.code.controlecandidatosapi.candidatos.dto.CandidatoRequest;
import com.code.controlecandidatosapi.candidatos.dto.CandidatoResponse;
import com.code.controlecandidatosapi.candidatos.model.Candidato;
import com.code.controlecandidatosapi.cartao.mapper.CartaoMapper;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public interface CandidatoMapper {

    CandidatoMapper INSTANCE = Mappers.getMapper(CandidatoMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    Candidato of(CandidatoRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    CandidatoResponse of(Candidato candidato);

    @BeforeMapping
    private void consolidarCartoes(CandidatoRequest request, @MappingTarget Candidato candidato) {
        candidato.setCartoes(request.getCartoes().stream()
            .map(CartaoMapper.INSTANCE::of)
            .collect(Collectors.toSet()));
    }

    @BeforeMapping
    private void consolidarCartoes(Candidato candidato, @MappingTarget CandidatoResponse candidatoResponse) {
        candidatoResponse.setCartoes(candidato.getCartoes().stream()
            .map(CartaoMapper.INSTANCE::of)
            .collect(Collectors.toSet()));
    }
}
