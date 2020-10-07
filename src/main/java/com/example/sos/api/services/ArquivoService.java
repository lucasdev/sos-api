package com.example.sos.api.services;

import com.example.sos.api.entities.Arquivo;
import com.example.sos.api.repositories.ArquivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArquivoService {
    private final ArquivoRepository arquivoRepository;

    public Arquivo salvar(String filename) {
        Arquivo arquivo = new Arquivo(filename);

        try {
            arquivo = arquivoRepository.save(arquivo);
        }
        catch (Exception ex) {
            throw new RuntimeException("Erro ao tentar salvar arquivo.");
        }

        return arquivo;
    }

    public List<Arquivo> obterTodos() {
        return arquivoRepository.findArquivos();
    }

    public Arquivo buscarUm(Long id) {
        Arquivo arquivo = arquivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arquivo não encontrado."));

        return arquivo;
    }
}
