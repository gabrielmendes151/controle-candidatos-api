package com.code.controlecandidatosapi.cartao.service;

import com.code.controlecandidatosapi.cartao.repository.CartaoRepository;
import com.code.controlecandidatosapi.enums.EBandeira;
import com.code.controlecandidatosapi.exception.ValidacaoException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static helper.Helper.umCartao;
import static helper.Helper.umCartaoRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartaoServiceTest {

    @Mock
    private CartaoRepository repository;
    @InjectMocks
    private CartaoService service;

    @DisplayName("lista todos cartoes salvos")
    @Test
    public void getAll_cartoes_quandoPossuir() {
        when(repository.findAll()).thenReturn(List.of(umCartao(), umCartao(), umCartao(), umCartao()));
        assertThat(service.getAll()).hasSize(4);
    }

    @DisplayName("lista cartoes por id")
    @Test
    public void findById_cartoes_quandoExistir() {
        var cartao = umCartao();
        cartao.setId(1);
        when(repository.findById(any())).thenReturn(Optional.of(cartao));
        var response = service.findById(1);

        assertThat(response.getId()).isEqualTo(1);
    }

    @DisplayName("deve retornar erro de cartao não encontrado")
    @Test
    public void findById_validacaoExecption_quandoNaoExistir() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> service.findById(1))
            .withMessage("Cartão não encontrado.");
    }

    @DisplayName("salva um cartão")
    @Test
    public void salvar_salvaCandidadto_quandoRequestOk() {
        var cartao = umCartao();
        cartao.setId(1);
        cartao.setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        when(repository.save(any())).thenReturn(cartao);

        var cartaoSalvo = service.salvar(umCartaoRequest());
        assertThat(cartaoSalvo.getId()).isEqualTo(1);
        assertThat(cartaoSalvo.getDataCadastro()).isNotNull();
        assertThat(cartaoSalvo.getTitular()).isEqualTo("GABRIEL TESTE");
        assertThat(cartaoSalvo.getNumero()).isEqualTo("448545649892");
        assertThat(cartaoSalvo.getDataVencimento()).isEqualTo("02/90");
        assertThat(cartaoSalvo.getBandeira()).isEqualTo(EBandeira.MASTERCARD);
        assertThat(cartaoSalvo.getCodigoSeguranca()).isEqualTo("111");

    }

    @DisplayName("altera um cartao")
    @Test
    public void alterar_alteraCartao_quandoRequestOk() {
        var cartao = umCartao();
        cartao.setId(1);
        cartao.setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        when(repository.save(any())).thenReturn(cartao);

        var cartaoSalvo = service.salvar(umCartaoRequest());
        assertThat(cartaoSalvo.getId()).isEqualTo(1);
        assertThat(cartaoSalvo.getDataCadastro()).isNotNull();
        assertThat(cartaoSalvo.getTitular()).isEqualTo("GABRIEL TESTE");
        assertThat(cartaoSalvo.getNumero()).isEqualTo("448545649892");
        assertThat(cartaoSalvo.getDataVencimento()).isEqualTo("02/90");
        assertThat(cartaoSalvo.getBandeira()).isEqualTo(EBandeira.MASTERCARD);
        assertThat(cartaoSalvo.getCodigoSeguranca()).isEqualTo("111");
    }


    @DisplayName("deve retornar erro de cartao não encontrado")
    @Test
    public void delete_validacaoExecption_quandoNaoExistirCartao() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> service.deletar(1))
            .withMessage("Cartão não encontrado.");
    }
}
