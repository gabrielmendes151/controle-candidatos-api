package com.code.controlecandidatosapi.candidatos.model;

import com.code.controlecandidatosapi.cartao.model.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "CANDIDATO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CANDIDATO")
    @SequenceGenerator(name = "SEQ_CANDIDATO", sequenceName = "SEQ_CANDIDATO")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "DATA_CADASTRO", updatable = false)
    private LocalDateTime dataCadastro;

    @JoinTable(name = "CANDIDATO_CARTAO",
        joinColumns = @JoinColumn(name = "CANDIDATO_ID",
            foreignKey = @ForeignKey(name = "FK_CANDIDATO"),
            referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "CARTAO_ID",
            foreignKey = @ForeignKey(name = "FK_CANDIDATO_CARTAO"),
            referencedColumnName = "id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Cartao> cartoes;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
    }
}
