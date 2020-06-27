package com.code.controlecandidatosapi.cartao.mapper;

import com.code.controlecandidatosapi.enums.EBandeira;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helper.Helper.umCartao;
import static helper.Helper.umCartaoRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class CartaoMapperTest {

    @DisplayName("converte a model cartao para dto de response")
    @Test
    public void of_cartaoToCartaoResponse_quandoPossuirInformacoes() {
        var response = CartaoMapper.INSTANCE.of(umCartao());
        assertThat(response.getId()).isNull();
        assertThat(response.getDataCadastro()).isNull();
        assertThat(response.getTitular()).isEqualTo("GABRIEL TESTE");
        assertThat(response.getNumero()).isEqualTo("448545649892");
        assertThat(response.getDataVencimento()).isEqualTo("02/90");
        assertThat(response.getBandeira()).isEqualTo(EBandeira.MASTERCARD);
        assertThat(response.getCodigoSeguranca()).isEqualTo("111");
    }

    @DisplayName("converte a dto de request para a model de cartao")
    @Test
    public void of_cartaoRequestToCartao_quandoPossuirInformacoes() {
        var cartao = CartaoMapper.INSTANCE.of(umCartaoRequest());
        assertThat(cartao.getId()).isNull();
        assertThat(cartao.getDataCadastro()).isNull();
        assertThat(cartao.getTitular()).isEqualTo("JOSE TESTE");
        assertThat(cartao.getNumero()).isEqualTo("448545699898");
        assertThat(cartao.getDataVencimento()).isEqualTo("02/99");
        assertThat(cartao.getBandeira()).isEqualTo(EBandeira.ELO);
        assertThat(cartao.getCodigoSeguranca()).isEqualTo("000");
    }
}