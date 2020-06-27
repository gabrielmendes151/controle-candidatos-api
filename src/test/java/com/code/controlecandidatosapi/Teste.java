package com.code.controlecandidatosapi;

import com.code.controlecandidatosapi.candidatos.dto.CandidatoRequest;
import com.code.controlecandidatosapi.candidatos.mapper.CandidatoMapper;
import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.code.controlecandidatosapi.enums.EBandeira;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class Teste {

    @Test
    public void testaResponseMapper(){
        var candidatoreq = CandidatoRequest.builder()
            .cartoes(Set.of(CartaoRequest.builder()
                .bandeira(EBandeira.ELO)
                .codigoSeguranca("12")
                .dataVencimento("02/02")
                .numero("dsas").titular("as").build()))
            .cpf("42323")
            .email("sdsd")
            .nome("asas")
            .build();
        CandidatoMapper.INSTANCE.of(candidatoreq);
    }
}
