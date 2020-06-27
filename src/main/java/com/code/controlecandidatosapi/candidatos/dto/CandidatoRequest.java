package com.code.controlecandidatosapi.candidatos.dto;

import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.sun.istack.NotNull;
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
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String cpf;
    @NotNull
    private Set<CartaoRequest> cartoes;
}
