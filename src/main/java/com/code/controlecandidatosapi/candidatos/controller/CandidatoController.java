package com.code.controlecandidatosapi.candidatos.controller;

import com.code.controlecandidatosapi.candidatos.dto.CandidatoRequest;
import com.code.controlecandidatosapi.candidatos.dto.CandidatoResponse;
import com.code.controlecandidatosapi.candidatos.mapper.CandidatoMapper;
import com.code.controlecandidatosapi.candidatos.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/candidatos")
public class CandidatoController {

    @Autowired
    private CandidatoService service;

    @GetMapping
    public List<CandidatoResponse> getAll(){
        return service.getAll().stream()
            .map(CandidatoMapper.INSTANCE::of)
            .collect(Collectors.toList());
    }

    @PostMapping
    public CandidatoResponse salvar(@Valid @RequestBody CandidatoRequest request){
        return CandidatoMapper.INSTANCE.of(service.salvar(request));
    }

    @PutMapping
    public CandidatoResponse alterar(@Valid @RequestBody CandidatoRequest request){
        return CandidatoMapper.INSTANCE.of(service.alterar(request));
    }

    @GetMapping("{id}")
    public CandidatoResponse getById(@PathVariable Integer id){
        return CandidatoMapper.INSTANCE.of(service.findById(id));
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Integer id){
        service.deletar(id);
    }
}
