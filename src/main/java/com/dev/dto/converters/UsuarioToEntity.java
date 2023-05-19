package com.dev.dto.converters;

import com.dev.domain.Usuario;
import com.dev.dto.UsuarioDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public enum UsuarioToEntity implements Function<UsuarioDTO, Usuario> {
    INSTANCE;

    @Override
    public Usuario apply(UsuarioDTO dto) {
        Usuario entity = new Usuario();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

            if (dto.getRolesDTO() != null) {
                entity.setRoles(dto.getRolesDTO().stream().map(RolToEntity.INSTANCE::apply).collect(Collectors.toList()));
            }
        }

        return entity;
    }
}