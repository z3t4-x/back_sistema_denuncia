package com.dev.dto.converters;

import com.dev.domain.Catalogos;
import com.dev.dto.CatalogosDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum CatalogosToEntity implements Function<CatalogosDTO, Catalogos> {

    INSTANCE;

    @Override
    public Catalogos apply(CatalogosDTO dto) {
        Catalogos entity = null;

        if (dto != null) {
             entity = new Catalogos();
            BeanUtils.copyProperties(dto, entity);
        }

        return entity;
    }
}