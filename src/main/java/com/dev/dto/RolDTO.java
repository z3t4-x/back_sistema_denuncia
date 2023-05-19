package com.dev.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RolDTO extends AuditoriaDTO implements Serializable {

    private Long idRol;
    private String nombreRol;
    private List<UsuarioDTO> usuariosDTO;

    public RolDTO() {
        this.usuariosDTO = new ArrayList<>();
    }

    public void addUsuarioDTO(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            this.usuariosDTO.add(usuarioDTO);
        }
    }

    public void removeUsuarioDTO(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            this.usuariosDTO.remove(usuarioDTO);
        }
    }

}
