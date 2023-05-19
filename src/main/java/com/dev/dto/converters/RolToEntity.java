package com.dev.dto.converters;

import com.dev.domain.Rol;
import com.dev.dto.RolDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public enum RolToEntity implements Function<RolDTO, Rol> {
    INSTANCE;

    @Override
    public Rol apply(RolDTO dto) {
        Rol entity = new Rol();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

            if (dto.getUsuariosDTO() != null) {
                entity.setUsuarios(dto.getUsuariosDTO().stream().map(UsuarioToEntity.INSTANCE::apply).collect(Collectors.toList()));
            }
        }
        return entity;
    }
}
