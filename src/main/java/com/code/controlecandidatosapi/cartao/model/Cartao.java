package com.code.controlecandidatosapi.cartao.model;

import com.code.controlecandidatosapi.enums.EBandeira;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CARTAO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CARTAO")
    @SequenceGenerator(name = "SEQ_CARTAO", sequenceName = "SEQ_CARTAO")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITULAR")
    private String titular;

    @Column(name = "BANDEIRA")
    @Enumerated(EnumType.STRING)
    private EBandeira bandeira;

    @Column(name = "NUMERO", length = 16)
    private String numero;

    @Column(name = "COD_SEGURANCA", length = 3)
    private String codigoSeguranca;

    @Column(name = "DATA_VENCIMENTO", length = 5)
    private String dataVencimento;

    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    @PrePersist
    public void prePersist() {
        dataCadastro = LocalDateTime.now();
    }
}
