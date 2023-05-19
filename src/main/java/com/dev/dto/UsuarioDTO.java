package com.dev.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO extends  AuditoriaDTO implements Serializable {

    private Long idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private CatalogosValoresDTO fiscalia;
    private List<RolDTO> rolesDTO;

    public UsuarioDTO() {
        this.rolesDTO = new ArrayList<>();
        fiscalia =new CatalogosValoresDTO();
    }

}
