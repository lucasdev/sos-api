package com.example.sos.api.dto.mapper;

import com.example.sos.api.dto.MarcaDTO;
import com.example.sos.api.entities.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MarcaMapper {
    Marca toModel(MarcaDTO dto);
    MarcaDTO toDto(Marca model);

    List<Marca> toModel(List<MarcaDTO> dto);
    List<MarcaDTO> toDto(List<Marca> model);
}
