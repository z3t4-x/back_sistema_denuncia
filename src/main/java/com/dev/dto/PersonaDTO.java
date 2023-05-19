package com.dev.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PersonaDTO extends AuditoriaDTO implements Serializable {

    private Integer idPersona;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private CatalogosValoresDTO grado;
    private CatalogosValoresDTO genero;
    private CatalogosValoresDTO tipoIdentificacion;
    private String dni;
    private CatalogosValoresDTO tipoInstitucion;
    private LocalDate fcNacimiento;
    private String telefono;

    private UsuarioDTO usuarioDTO;
   // private List<DenunciaPersonaDTO> lstDenunciasPersonas;

    ///////////////////////////////////////////////
    //////////////////////// CONSTRUCTOR
    ///////////////////////////////////////////////

    public PersonaDTO() {
        super();
        this.grado = new CatalogosValoresDTO();
        this.genero = new CatalogosValoresDTO();
        this.tipoIdentificacion = new CatalogosValoresDTO();
        this.tipoInstitucion = new CatalogosValoresDTO();
      //  lstDenunciasPersonas = new ArrayList<>();
    }
}
