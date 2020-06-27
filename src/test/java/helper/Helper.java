package helper;

import com.code.controlecandidatosapi.candidatos.dto.CandidatoRequest;
import com.code.controlecandidatosapi.candidatos.model.Candidato;
import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.code.controlecandidatosapi.cartao.model.Cartao;
import com.code.controlecandidatosapi.enums.EBandeira;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

import java.util.Set;

public class Helper {

    private static ObjectMapper mapper = getMapper();

    @SneakyThrows
    public static byte[] convertObjectToJsonBytes(Object object) {
        return mapper.writeValueAsBytes(object);
    }

    private static ObjectMapper getMapper() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static Candidato umCandidato() {
        return Candidato.builder()
            .cartoes(Set.of(umCartao()))
            .cpf("12282244190")
            .email("gabrielmendes@email.com")
            .nome("Gabriel")
            .build();
    }

    public static CandidatoRequest umCandidatoRequest() {
        return CandidatoRequest.builder()
            .cartoes(Set.of(umCartaoRequest()))
            .cpf("12283344190")
            .email("jose@email.com")
            .nome("Jose")
            .build();
    }

    public static CartaoRequest umCartaoRequest() {
        return CartaoRequest.builder()
            .bandeira(EBandeira.ELO)
            .codigoSeguranca("000")
            .dataVencimento("02/99")
            .numero("448545699898")
            .titular("JOSE TESTE")
            .build();
    }


    public static Cartao umCartao() {
        return Cartao.builder()
            .bandeira(EBandeira.MASTERCARD)
            .codigoSeguranca("111")
            .dataVencimento("02/90")
            .numero("448545649892")
            .titular("GABRIEL TESTE")
            .build();
    }


}
