package com.dev.dto.converters;

import com.dev.domain.CatalogosValores;
import com.dev.dto.CatalogosValoresDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum CatalogosValoresToDTO implements Function<CatalogosValores, CatalogosValoresDTO> {

    INSTANCE;

    @Override
    public CatalogosValoresDTO apply(CatalogosValores entity) {
        CatalogosValoresDTO dto = new CatalogosValoresDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            dto.setCatalogoDTO(CatalogosToDTO.INSTANCE.apply(entity.getCatalogo()));
        }

        return dto;
    }
}