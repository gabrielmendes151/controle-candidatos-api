package com.code.controlecandidatosapi.candidatos.service;

import com.code.controlecandidatosapi.candidatos.repository.CandidatoRepository;
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

import static helper.Helper.umCandidato;
import static helper.Helper.umCandidatoRequest;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CandidatoServiceTest {

    @Mock
    private CandidatoRepository repository;
    @InjectMocks
    private CandidatoService service;

    @DisplayName("lista todos candidatos salvos")
    @Test
    public void getAll_candidatos_quandoPossuir() {
        when(repository.findAll()).thenReturn(List.of(umCandidato(), umCandidato(), umCandidato(), umCandidato()));
        assertThat(service.getAll()).hasSize(4);
    }

    @DisplayName("lista candidato por id")
    @Test
    public void findById_candidato_quandoExistir() {
        var candidato = umCandidato();
        candidato.setId(1);
        when(repository.findById(any())).thenReturn(Optional.of(candidato));
        var response = service.findById(1);

        assertThat(response.getId()).isEqualTo(1);
    }

    @DisplayName("deve retornar erro de candidato não encontrado")
    @Test
    public void findById_validacaoExecption_quandoNaoExistir() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> service.findById(1))
            .withMessage("Candidato não encontrado.");
    }

    @DisplayName("salva um candidato")
    @Test
    public void salvar_salvaCandidadto_quandoRequestOk() {
        var candidato = umCandidato();
        candidato.setId(1);
        candidato.getCartoes().iterator().next().setId(1);
        candidato.getCartoes().iterator().next()
            .setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        candidato.setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        when(repository.save(any())).thenReturn(candidato);

        var candidatoSalvo = service.salvar(umCandidatoRequest());
        assertThat(candidatoSalvo.getId()).isEqualTo(1);
        assertThat(candidatoSalvo.getDataCadastro()).isNotNull();
        assertThat(candidatoSalvo.getNome()).isEqualTo("Gabriel");
        assertThat(candidatoSalvo.getEmail()).isEqualTo("gabrielmendes@email.com");
        assertThat(candidatoSalvo.getCpf()).isEqualTo("12282244190");
        assertThat(candidatoSalvo.getCartoes())
            .hasSize(1)
            .extracting("id", "bandeira", "dataVencimento", "numero", "titular", "codigoSeguranca",
                "dataCadastro")
            .containsExactly(
                tuple(1, EBandeira.MASTERCARD, "02/90", "448545649892", "GABRIEL TESTE", "111",
                    LocalDateTime.of(2020, 6, 27, 8, 0, 0))
            );
    }

    @DisplayName("altera um candidato")
    @Test
    public void alterar_alteraCandidato_quandoRequestOk() {
        var candidato = umCandidato();
        candidato.setId(1);
        candidato.getCartoes().iterator().next().setId(1);
        candidato.getCartoes().iterator().next()
            .setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        candidato.setDataCadastro(LocalDateTime.of(2020, 6, 27, 8, 0, 0));
        when(repository.save(any())).thenReturn(candidato);

        var candidatoAlterado = service.alterar(umCandidatoRequest());
        assertThat(candidatoAlterado.getId()).isEqualTo(1);
        assertThat(candidatoAlterado.getDataCadastro()).isNotNull();
        assertThat(candidatoAlterado.getNome()).isEqualTo("Gabriel");
        assertThat(candidatoAlterado.getEmail()).isEqualTo("gabrielmendes@email.com");
        assertThat(candidatoAlterado.getCpf()).isEqualTo("12282244190");
        assertThat(candidatoAlterado.getCartoes())
            .hasSize(1)
            .extracting("id", "bandeira", "dataVencimento", "numero", "titular", "codigoSeguranca",
                "dataCadastro")
            .containsExactly(
                tuple(1, EBandeira.MASTERCARD, "02/90", "448545649892", "GABRIEL TESTE", "111",
                    LocalDateTime.of(2020, 6, 27, 8, 0, 0))
            );
    }


    @DisplayName("deve retornar erro de candidato não encontrado")
    @Test
    public void delete_validacaoExecption_quandoNaoExistirCandidato() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(() -> service.deletar(1))
            .withMessage("Candidato não encontrado.");
    }
}
