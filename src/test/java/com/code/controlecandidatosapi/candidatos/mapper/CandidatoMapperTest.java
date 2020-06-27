package com.code.controlecandidatosapi.candidatos.mapper;

import com.code.controlecandidatosapi.enums.EBandeira;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helper.Helper.umCandidato;
import static helper.Helper.umCandidatoRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CandidatoMapperTest {

    @DisplayName("converte a model candidato para dto de response")
    @Test
    public void of_candidatoToCandidatoResponse_quandoPossuirInformacoes() {
        var response = CandidatoMapper.INSTANCE.of(umCandidato());
        assertThat(response.getId()).isNull();
        assertThat(response.getDataCadastro()).isNull();
        assertThat(response.getNome()).isEqualTo("Gabriel");
        assertThat(response.getEmail()).isEqualTo("gabrielmendes@email.com");
        assertThat(response.getCpf()).isEqualTo("12282244190");
        assertThat(response.getCartoes())
            .hasSize(1)
            .extracting("id", "bandeira", "dataVencimento", "numero", "titular", "codigoSeguranca",
                "dataCadastro")
            .containsExactly(
                tuple(null, EBandeira.MASTERCARD, "02/90", "448545649892", "GABRIEL TESTE", "111", null)
            );
    }

    @DisplayName("converte a dto de request para a model de candidato")
    @Test
    public void of_candidatoRequestToCandidato_quandoPossuirInformacoes() {
        var response = CandidatoMapper.INSTANCE.of(umCandidatoRequest());
        assertThat(response.getId()).isNull();
        assertThat(response.getDataCadastro()).isNull();
        assertThat(response.getNome()).isEqualTo("Jose");
        assertThat(response.getEmail()).isEqualTo("jose@email.com");
        assertThat(response.getCpf()).isEqualTo("12283344190");
        assertThat(response.getCartoes())
            .hasSize(1)
            .extracting("id", "bandeira", "dataVencimento", "numero", "titular", "codigoSeguranca",
                "dataCadastro")
            .containsExactly(
                tuple(null, EBandeira.ELO, "02/99", "448545699898", "JOSE TESTE", "000", null)
            );
    }
}
