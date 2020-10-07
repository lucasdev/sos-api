package com.example.sos.api.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_arquivo")
public class Arquivo implements Serializable {
    private static final long serialVersionUID = 7070543790247724272L;

    public Arquivo() {
    }

    public Arquivo(String path) {
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
}
