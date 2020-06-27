package com.code.controlecandidatosapi.cartao.controller;

import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.code.controlecandidatosapi.cartao.dto.CartaoResponse;
import com.code.controlecandidatosapi.cartao.mapper.CartaoMapper;
import com.code.controlecandidatosapi.cartao.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cartoes")
@CrossOrigin
public class CartaoController {

    @Autowired
    private CartaoService service;

    @GetMapping
    public List<CartaoResponse> getAll() {
        return service.getAll().stream()
            .map(CartaoMapper.INSTANCE::of)
            .collect(Collectors.toList());
    }

    @PostMapping
    public CartaoResponse salvar(@Valid @RequestBody CartaoRequest request) {
        return CartaoMapper.INSTANCE.of(service.salvar(request));
    }

    @PutMapping
    public CartaoResponse alterar(@Valid @RequestBody CartaoRequest request) {
        return CartaoMapper.INSTANCE.of(service.alterar(request));
    }

    @GetMapping("{id}")
    public CartaoResponse getById(@PathVariable Integer id) {
        return CartaoMapper.INSTANCE.of(service.findById(id));
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
