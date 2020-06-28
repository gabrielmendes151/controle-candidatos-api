package com.code.controlecandidatosapi.cartao.controller;

import com.code.controlecandidatosapi.cartao.service.CartaoService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(CartaoController.class)
@ActiveProfiles("test")
public class CartaoControllerTest {

    private static final String PATH = "/api/cartoes";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartaoService service;
    @Autowired
    private WebApplicationContext webApplicationContex;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContex).build();
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getAll_cartoes_quandoPossuir() {
        when(service.getAll()).thenReturn(List.of(umCartao(), umCartao()));
        mockMvc.perform(get(PATH))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void salvar_salvaCartao_quandoInformacoesOk() {
        var cartao = umCartao();
        cartao.setId(1);
        when(service.salvar(any())).thenReturn(cartao);

        mockMvc.perform(post(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonBytes(umCandidatoRequest()))
            .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.titular", is("GABRIEL TESTE")))
            .andExpect(jsonPath("$.bandeira", is("MASTERCARD")))
            .andExpect(jsonPath("$.codigoSeguranca", is("111")))
            .andExpect(jsonPath("$.dataVencimento", is("02/90")))
            .andExpect(jsonPath("$.numero", is("448545649892")));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void alterar_alteraCartao_quandoInformacoesOk() {
        var cartao = umCartao();
        cartao.setId(1);
        when(service.salvar(any())).thenReturn(cartao);

        mockMvc.perform(post(PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonBytes(umCandidatoRequest()))
            .characterEncoding("utf-8"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.titular", is("GABRIEL TESTE")))
            .andExpect(jsonPath("$.bandeira", is("MASTERCARD")))
            .andExpect(jsonPath("$.codigoSeguranca", is("111")))
            .andExpect(jsonPath("$.dataVencimento", is("02/90")))
            .andExpect(jsonPath("$.numero", is("448545649892")));
    }

    @SneakyThrows
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getById_cartao_quandoPossuir() {
        when(service.findById(any())).thenReturn(umCartao());
        mockMvc.perform(get(PATH)
            .param("id", "1"))
            .andExpect(status().isOk());
    }
}
