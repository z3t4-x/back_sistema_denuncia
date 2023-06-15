package com.dev.dto.converters;

import com.dev.domain.Rol;
import com.dev.dto.RolDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum RolToDTO implements Function<Rol, RolDTO> {

    INSTANCE;

    @Override
    public RolDTO apply(Rol entity) {
        RolDTO dto = new RolDTO();
        if (entity != null) {
          //  BeanUtils.copyProperties(entity, dto);
            dto.setIdRol(entity.getIdRol());
            dto.setRolNombre(entity.getRolNombre());
        }
        return dto;
    }

}
