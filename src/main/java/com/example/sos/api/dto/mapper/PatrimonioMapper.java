package com.example.sos.api.dto.mapper;

import com.example.sos.api.dto.PatrimonioDTO;
import com.example.sos.api.entities.Patrimonio;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatrimonioMapper {
    Patrimonio toModel(PatrimonioDTO dto);
    PatrimonioDTO toDto(Patrimonio model);
    List<Patrimonio> toModel(List<PatrimonioDTO> dto);
    List<PatrimonioDTO> toDto(List<Patrimonio> model);
}
