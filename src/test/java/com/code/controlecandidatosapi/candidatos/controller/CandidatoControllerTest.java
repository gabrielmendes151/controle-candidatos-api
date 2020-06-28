package com.code.controlecandidatosapi.candidatos.controller;

import com.code.controlecandidatosapi.candidatos.service.CandidatoService;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static helper.Helper.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(CandidatoController.class)
@ActiveProfiles("test")
public class CandidatoControllerTest {

    private static final String PATH = "/api/candidatos";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CandidatoService service;
    @Autowired
    private WebApplicationContext webApplicationContex;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContex).build();
    }

    @Test
    @SneakyThrows
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAll_candidatos_quandoPossuirAutenticacao() {
        when(service.getAll()).thenReturn(List.of(umCandidato(), umCandidato()));
        mockMvc.perform(get(PATH))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void salvar_salvaCandidato_quandoInformacoesOk() {
        var candidato = umCandidato();
        candidato.setId(1);
        when(service.salvar(any())).thenReturn(candidato);

        mockMvc.perform(post(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonBytes(umCandidatoRequest()))
            .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.nome", is("Gabriel")))
            .andExpect(jsonPath("$.email", is("gabrielmendes@email.com")))
            .andExpect(jsonPath("$.cpf", is("12282244190")))
            .andExpect(jsonPath("$.cartoes[0].numero", is("448545649892")))
            .andExpect(jsonPath("$.cartoes[0].titular", is("GABRIEL TESTE")))
            .andExpect(jsonPath("$.cartoes[0].dataVencimento", is("02/90")))
            .andExpect(jsonPath("$.cartoes[0].bandeira", is("MASTERCARD")))
            .andExpect(jsonPath("$.cartoes[0].codigoSeguranca", is("111")));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void alterar_alteraCandidato_quandoInformacoesOk() {
        var candidato = umCandidato();
        candidato.setId(1);
        when(service.alterar(any())).thenReturn(candidato);

        mockMvc.perform(put(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonBytes(umCandidatoRequest()))
            .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.nome", is("Gabriel")))
            .andExpect(jsonPath("$.email", is("gabrielmendes@email.com")))
            .andExpect(jsonPath("$.cpf", is("12282244190")))
            .andExpect(jsonPath("$.cartoes[0].numero", is("448545649892")))
            .andExpect(jsonPath("$.cartoes[0].titular", is("GABRIEL TESTE")))
            .andExpect(jsonPath("$.cartoes[0].dataVencimento", is("02/90")))
            .andExpect(jsonPath("$.cartoes[0].bandeira", is("MASTERCARD")))
            .andExpect(jsonPath("$.cartoes[0].codigoSeguranca", is("111")));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getById_candidato_quandoPossuir() {
        when(service.findById(any())).thenReturn(umCandidato());
        mockMvc.perform(get(PATH)
            .param("id", "1"))
            .andExpect(status().isOk());
    }
}
