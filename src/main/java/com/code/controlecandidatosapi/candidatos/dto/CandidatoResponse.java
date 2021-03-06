package com.code.controlecandidatosapi.candidatos.dto;

import com.code.controlecandidatosapi.cartao.dto.CartaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoResponse {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private Set<CartaoResponse> cartoes;
    private LocalDateTime dataCadastro;
}
