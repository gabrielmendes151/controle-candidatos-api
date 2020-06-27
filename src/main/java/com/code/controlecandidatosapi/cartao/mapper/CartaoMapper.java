package com.code.controlecandidatosapi.cartao.mapper;

import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.code.controlecandidatosapi.cartao.dto.CartaoResponse;
import com.code.controlecandidatosapi.cartao.model.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartaoMapper {

    CartaoMapper INSTANCE = Mappers.getMapper(CartaoMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "titular", target = "titular")
    @Mapping(source = "bandeira", target = "bandeira")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "codigoSeguranca", target = "codigoSeguranca")
    @Mapping(source = "dataVencimento", target = "dataVencimento")
    CartaoResponse of(Cartao cartao);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "titular", target = "titular")
    @Mapping(source = "bandeira", target = "bandeira")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "codigoSeguranca", target = "codigoSeguranca")
    @Mapping(source = "dataVencimento", target = "dataVencimento")
    Cartao of(CartaoRequest cartaoRequest);
}
