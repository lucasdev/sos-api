package com.example.sos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatrimonioDTO implements Serializable {
    private static final long serialVersionUID = 7411748175718494291L;
    private Long id;
    private String nome;
    private String descricao;
    private String nuTombo;
    private MarcaDTO marca;
}
