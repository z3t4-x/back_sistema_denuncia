package com.dev.dto.converters;

import com.dev.domain.Rol;
import com.dev.domain.Usuario;
import com.dev.dto.RolDTO;
import com.dev.dto.UsuarioDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum UsuarioToEntity implements Function<UsuarioDTO, Usuario> {
    INSTANCE;

    @Override
    public Usuario apply(UsuarioDTO dto) {
        Usuario entity = new Usuario();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

//            if (dto.getRolesDTO() != null) {
//                entity.setRoles(dto.getRolesDTO().stream().map(RolToEntity.INSTANCE::apply).collect(Collectors.toList()));
//            }

            if (dto.getRolesDTO() != null) {
                List<Rol> roles = dto.getRolesDTO().stream()
                        .map(rolDto -> RolToEntity.INSTANCE.apply(rolDto))
                        .collect(Collectors.toList());
                entity.setRoles(roles);
            }


            if (dto.getFiscalia() != null) {
                entity.setFiscalia(CatalogosValoresToEntity.INSTANCE.apply(dto.getFiscalia()));
            }

            if (dto.getMesaParte() != null) {
                entity.setMesaParte(CatalogosValoresToEntity.INSTANCE.apply(dto.getMesaParte()));
            }
        }

        return entity;
    }
}