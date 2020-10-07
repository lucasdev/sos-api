package com.example.sos.api.services;

import com.example.sos.api.dto.MarcaDTO;
import com.example.sos.api.dto.PatrimonioDTO;
import com.example.sos.api.dto.mapper.MarcaMapper;
import com.example.sos.api.dto.mapper.PatrimonioMapper;
import com.example.sos.api.entities.Marca;
import com.example.sos.api.entities.Patrimonio;
import com.example.sos.api.repositories.MarcaRepository;
import com.example.sos.api.repositories.PatrimonioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatrimonioService {
    private final PatrimonioRepository patrimonioRepository;
    private final PatrimonioMapper patrimonioMapper;
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;
    private static final String NOT_FOUNT_ENTITY = "registro nao encontrado";

    public Page<PatrimonioDTO> fetchAllWithPaginate(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Patrimonio> all = patrimonioRepository.findAll(pageable);
        return new PageImpl<>(patrimonioMapper.toDto(all.getContent()), all.getPageable(), all.getTotalElements());
    }

    public Page<PatrimonioDTO> fetchAllByMarcaWithPaginate(MarcaDTO marcaDTO, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Patrimonio> all = patrimonioRepository.findAllByMarca(marcaMapper.toModel(marcaDTO), pageable);
        return new PageImpl<>(patrimonioMapper.toDto(all.getContent()), all.getPageable(), all.getTotalElements());
    }

    public PatrimonioDTO findById(Long id) {
        Patrimonio patrimonio = patrimonioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

        return patrimonioMapper.toDto(patrimonio);
    }

    public PatrimonioDTO save(PatrimonioDTO patrimonioDTO) {
        try {
            Patrimonio patrimonio = patrimonioMapper.toModel(patrimonioDTO);
            Marca marca = marcaRepository
                    .findById(patrimonio.getMarca().getId())
                    .orElseThrow(() -> new RuntimeException("Erro ao busar entidade"));

            patrimonio.setMarca(marca);
            Patrimonio save = patrimonioRepository.save(patrimonio);
            return patrimonioMapper.toDto(save);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir");
        }
    }

    public void update(PatrimonioDTO patrimonioDTO, Long id) {
        try {
            Patrimonio patrimonio = patrimonioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

            patrimonio.setNome(patrimonioDTO.getNome());
            patrimonio.setDescricao(patrimonioDTO.getDescricao());
            patrimonio.setMarca(marcaMapper.toModel(patrimonioDTO.getMarca()));
            patrimonioRepository.save(patrimonio);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar");
        }
    }

    public void delete(Long id) {
        try {
            Patrimonio patrimonio = patrimonioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(NOT_FOUNT_ENTITY));

            patrimonioRepository.delete(patrimonio);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao apagar");
        }
    }
}
