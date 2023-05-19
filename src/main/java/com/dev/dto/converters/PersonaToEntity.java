package com.dev.dto.converters;

import com.dev.domain.Persona;
import com.dev.dto.PersonaDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum PersonaToEntity implements Function<PersonaDTO, Persona> {

    INSTANCE;

    @Override
    public Persona apply(PersonaDTO dto) {
        Persona entity = null;

        if (dto != null) {
            entity = new Persona();
            BeanUtils.copyProperties(dto, entity);

            if (dto.getGrado() != null && dto.getGrado().getIdValor() != null) {
                entity.setGrado(CatalogosValoresToEntity.INSTANCE.apply(dto.getGrado()));
            }
            if (dto.getGenero() != null && dto.getGenero().getIdValor() != null) {
                entity.setGenero(CatalogosValoresToEntity.INSTANCE.apply(dto.getGenero()));
            }
            if (dto.getTipoIdentificacion() != null && dto.getTipoIdentificacion().getIdValor() != null) {
                entity.setTipoIdentificacion(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoIdentificacion()));
            }
            if (dto.getTipoInstitucion() != null && dto.getTipoInstitucion().getIdValor() != null) {
                entity.setTipoInstitucion(CatalogosValoresToEntity.INSTANCE.apply(dto.getTipoInstitucion()));
            }
        }

        return entity;
    }

}
