package com.code.controlecandidatosapi.candidatos.dto;

import com.code.controlecandidatosapi.candidatos.model.Candidato;
import com.code.controlecandidatosapi.cartao.dto.CartaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

    public static CandidatoResponse of(Candidato candidato) {
        var response = new CandidatoResponse();
        BeanUtils.copyProperties(candidato, response);
        response.setCartoes(CartaoResponse.of(candidato.getCartoes()));
        return response;
    }
}
