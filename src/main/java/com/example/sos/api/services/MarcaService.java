package com.example.sos.api.services;

import com.example.sos.api.dto.MarcaDTO;
import com.example.sos.api.dto.mapper.MarcaMapper;
import com.example.sos.api.entities.Marca;
import com.example.sos.api.repositories.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    private static final String NOT_FOUNT_ENTITY = "registro nao encontrado";

    public Page<MarcaDTO> fetchAllWithPaginate(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Marca> all = marcaRepository.findAll(pageable);
        List<MarcaDTO> marcaDTOS = marcaMapper.toDto(all.getContent());
        return new PageImpl<>(marcaDTOS, all.getPageable(), all.getTotalElements());
    }

    public MarcaDTO findById(Long id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

        return marcaMapper.toDto(marca);
    }

    public MarcaDTO findByNome(String nome) {
        Marca marca = marcaRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

        return marcaMapper.toDto(marca);
    }

    public MarcaDTO save(MarcaDTO marcaDTO) {
        try {
            marcaRepository.save(marcaMapper.toModel(marcaDTO));
            return marcaDTO;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir " + ex.getMessage());
        }
    }

    public void update(MarcaDTO marcaDTO, Long id) {
        try {
            Marca marca = marcaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

            marca.setNome(marcaDTO.getNome());
            marcaRepository.save(marca);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar");
        }
    }

    public void delete(Long id) {
        try {
            Marca marca = marcaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

            marcaRepository.delete(marca);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao apagar");
        }
    }
}
