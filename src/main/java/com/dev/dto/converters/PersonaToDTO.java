package com.dev.dto.converters;

import com.dev.domain.Persona;
import com.dev.dto.PersonaDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum PersonaToDTO implements Function<Persona, PersonaDTO> {

    INSTANCE;

    @Override
    public PersonaDTO apply(Persona entity) {
        PersonaDTO dto = new PersonaDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);

            this.replaceNull(dto, entity);

            if (entity.getGrado() != null) {
                dto.setGrado(CatalogosValoresToDTO.INSTANCE.apply(entity.getGrado()));
            }
            if (entity.getGenero() != null) {
                dto.setGenero(CatalogosValoresToDTO.INSTANCE.apply(entity.getGenero()));
            }
            if (entity.getTipoIdentificacion() != null) {
                dto.setTipoIdentificacion(CatalogosValoresToDTO.INSTANCE.apply(entity.getTipoIdentificacion()));
            }
            if (entity.getTipoInstitucion() != null) {
                dto.setTipoInstitucion(CatalogosValoresToDTO.INSTANCE.apply(entity.getTipoInstitucion()));
            }
        }

        return dto;
    }

    private void replaceNull(PersonaDTO dto, Persona entity) {

        if (StringUtils.isBlank(entity.getTelefono())) {
            dto.setTelefono(StringUtils.EMPTY);
        }
    }


}
