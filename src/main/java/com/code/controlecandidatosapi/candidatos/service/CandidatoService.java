package com.code.controlecandidatosapi.candidatos.service;

import com.code.controlecandidatosapi.candidatos.dto.CandidatoRequest;
import com.code.controlecandidatosapi.candidatos.model.Candidato;
import com.code.controlecandidatosapi.candidatos.repository.CandidatoRepository;
import com.code.controlecandidatosapi.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CandidatoService {

    private static ValidacaoException NAO_ENCONTRADO = new ValidacaoException("Candidato não encontrado.");

    @Autowired
    private CandidatoRepository repository;

    public List<Candidato> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Candidato salvar(CandidatoRequest request) {
        return repository.save(Candidato.of(request));
    }

    @Transactional
    public Candidato alterar(CandidatoRequest request) {
        return repository.save(Candidato.of(request));
    }

    public Candidato findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> NAO_ENCONTRADO);
    }

    @Transactional
    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
