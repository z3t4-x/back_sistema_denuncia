package com.dev.dto.converters;

import com.dev.domain.CatalogosValores;
import com.dev.dto.CatalogosValoresDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum CatalogosValoresToEntity implements Function<CatalogosValoresDTO, CatalogosValores> {

    INSTANCE;

    @Override
    public CatalogosValores apply(CatalogosValoresDTO dto) {
        CatalogosValores entity = null;

        if (dto != null) {
            entity = new CatalogosValores();
            BeanUtils.copyProperties(dto, entity);
            entity.setCatalogo(CatalogosToEntity.INSTANCE.apply(dto.getCatalogoDTO()));
        }

        return entity;
    }
}