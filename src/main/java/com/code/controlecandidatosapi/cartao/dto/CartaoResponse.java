package com.code.controlecandidatosapi.cartao.dto;

import com.code.controlecandidatosapi.enums.EBandeira;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoResponse {

    private Integer id;
    private String titular;
    private EBandeira bandeira;
    private String numero;
    private String codigoSeguranca;
    private String dataVencimento;
    private String dataCadastro;
}
