package com.dev.dto.converters;

import com.dev.domain.Usuario;
import com.dev.dto.UsuarioDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;
import java.util.stream.Collectors;

public enum UsuarioToDTO implements Function<Usuario, UsuarioDTO> {

    INSTANCE;

    @Override
    public UsuarioDTO apply(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
            if (entity.getRoles() != null) {
                dto.setRolesDTO(entity.getRoles().stream()
                        .map(RolToDTO.INSTANCE::apply)
                        .collect(Collectors.toList()));
            }
            if (entity.getFiscalia() != null) {
                dto.setFiscalia(CatalogosValoresToDTO.INSTANCE.apply(entity.getFiscalia()));
            }

            if (entity.getMesaParte() != null) {
                dto.setMesaParte(CatalogosValoresToDTO.INSTANCE.apply(entity.getMesaParte()));
            }
        }
        return dto;
    }

}