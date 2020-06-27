package com.code.controlecandidatosapi.cartao.dto;

import com.code.controlecandidatosapi.enums.EBandeira;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartaoRequest {

    private Integer id;
    @NotNull
    private String titular;
    @NotNull
    private EBandeira bandeira;
    @NotNull
    private String numero;
    @NotNull
    private String codigoSeguranca;
    @NotNull
    private String dataVencimento;
}
