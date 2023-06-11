package com.dev.dto.converters;

import com.dev.domain.DenunciaPersona;
import com.dev.dto.DenunciaPersonaDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum DenunciaPersonaToEntity implements Function<DenunciaPersonaDTO, DenunciaPersona> {

    INSTANCE;

    @Override
    public DenunciaPersona apply(DenunciaPersonaDTO dto) {
        DenunciaPersona entity = new DenunciaPersona();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);


            if (dto.getPersonaDTO() != null) {
                entity.setPersona(PersonaToEntity.INSTANCE.apply(dto.getPersonaDTO()));
            }
            if (dto.getTipoPersona() != null) {
                entity.setTipoPersona(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoPersona()));
            }
        }

        return entity;
    }

}
