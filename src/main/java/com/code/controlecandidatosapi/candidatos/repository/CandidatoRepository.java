package com.code.controlecandidatosapi.candidatos.repository;

import com.code.controlecandidatosapi.candidatos.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {
}
