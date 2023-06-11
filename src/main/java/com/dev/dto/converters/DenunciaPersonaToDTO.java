package com.dev.dto.converters;

import com.dev.domain.DenunciaPersona;
import com.dev.dto.DenunciaPersonaDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum DenunciaPersonaToDTO implements Function<DenunciaPersona, DenunciaPersonaDTO> {

    INSTANCE;

    @Override
    public DenunciaPersonaDTO apply(DenunciaPersona entity) {
        DenunciaPersonaDTO dto = new DenunciaPersonaDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);

            if (entity.getPersona() != null) {
                dto.setPersonaDTO(PersonaToDTO.INSTANCE.apply(entity.getPersona()));
            }
            if (entity.getTipoPersona() != null) {
                dto.setTipoPersona(CatalogosValoresToDTO.INSTANCE.apply(entity.getTipoPersona()));
            }
        }

        return dto;
    }
}
