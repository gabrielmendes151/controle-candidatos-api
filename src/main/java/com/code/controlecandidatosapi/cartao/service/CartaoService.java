package com.code.controlecandidatosapi.cartao.service;

import com.code.controlecandidatosapi.cartao.dto.CartaoRequest;
import com.code.controlecandidatosapi.cartao.mapper.CartaoMapper;
import com.code.controlecandidatosapi.cartao.model.Cartao;
import com.code.controlecandidatosapi.cartao.repository.CartaoRepository;
import com.code.controlecandidatosapi.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartaoService {

    private static ValidacaoException NAO_ENCONTRADO = new ValidacaoException("Cartão não encontrado.");

    @Autowired
    private CartaoRepository repository;

    public List<Cartao> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Cartao salvar(CartaoRequest request) {
        return repository.save(CartaoMapper.INSTANCE.of(request));
    }

    @Transactional
    public Cartao alterar(CartaoRequest request) {
        return repository.save(CartaoMapper.INSTANCE.of(request));
    }

    public Cartao findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> NAO_ENCONTRADO);
    }

    @Transactional
    public void deletar(Integer id) {
        var cartao = this.findById(id);
        repository.delete(cartao);
    }
}
