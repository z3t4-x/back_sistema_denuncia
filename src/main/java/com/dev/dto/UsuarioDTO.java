package com.dev.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO extends  AuditoriaDTO implements Serializable {

    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private String cdUsuario;
    private String password;
    private String email;
    private CatalogosValoresDTO mesaParte;
    private CatalogosValoresDTO fiscalia;
    private List<RolDTO> rolesDTO;

    public UsuarioDTO() {
        this.rolesDTO = new ArrayList<>();
        this.fiscalia =new CatalogosValoresDTO();
        this.mesaParte =new CatalogosValoresDTO();
    }

}
