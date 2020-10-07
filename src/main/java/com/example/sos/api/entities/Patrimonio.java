package com.example.sos.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_patrimonio")
public class Patrimonio implements Serializable {
    private static final long serialVersionUID = -671952469199437295L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Column(name = "nu_tombo")
    private String nuTombo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
