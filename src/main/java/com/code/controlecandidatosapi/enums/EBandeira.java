package com.code.controlecandidatosapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EBandeira {

    VISA("Visa"),
    MASTERCARD("MasterCard"),
    ELO("Elo");

    private String descricao;
}
