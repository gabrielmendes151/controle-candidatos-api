package com.code.controlecandidatosapi.candidatos.dto;

import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoRequest {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private Set<CartaoRequest> cartoes;
}
