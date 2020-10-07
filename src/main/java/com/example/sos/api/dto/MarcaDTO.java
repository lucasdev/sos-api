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
public class MarcaDTO implements Serializable {
    private static final long serialVersionUID = -171712277242233438L;
    private Long id;
    private String nome;
}
