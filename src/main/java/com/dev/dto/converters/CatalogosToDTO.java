package com.dev.dto.converters;


import com.dev.domain.Catalogos;
import com.dev.dto.CatalogosDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

public enum CatalogosToDTO implements Function<Catalogos, CatalogosDTO> {

    INSTANCE;

    @Override
    public CatalogosDTO apply(Catalogos entity) {
        CatalogosDTO dto = new CatalogosDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }




}

