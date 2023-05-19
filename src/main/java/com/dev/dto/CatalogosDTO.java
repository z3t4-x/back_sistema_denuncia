package com.dev.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class CatalogosDTO extends AuditoriaDTO implements Serializable {

    ///////////////////////////////////////////////
    //////////////////////// CONSTRUCTOR
    ///////////////////////////////////////////////
    public CatalogosDTO() {
        super();
        this.dsNombre = StringUtils.EMPTY;
        this.tlDescripcion = StringUtils.EMPTY;
        this.lstCatalogosValores = new ArrayList<>();
        this.itMantenible = StringUtils.EMPTY;
    }

    /**
     * Constructor idCatalogo
     *
     * @param idCatalogo
     */
    public CatalogosDTO(Integer idCatalogo) {
        super();
        this.idCatalogo = idCatalogo;
        this.dsNombre = StringUtils.EMPTY;
        this.tlDescripcion = StringUtils.EMPTY;
        this.lstCatalogosValores = new ArrayList<>();
        this.itMantenible = StringUtils.EMPTY;
    }

    // ////////////////////////////////////////////////////////
    // /////////////////////////////// ATRIBUTOS
    // ////////////////////////////////////////////////////////

    /**
     * Identificador del numero de serie para serialización
     */
    private static final long serialVersionUID = 1L;

    /**
     * Campo idCatalogo
     */
    private Integer idCatalogo;

    /**
     * Campo dsNombre
     */
    private String dsNombre;

    /**
     * Campo tlDescripcion
     */
    private String tlDescripcion;

    /**
     * Campo saiiCatalogosValores
     */
    @JsonIgnore
    private List<CatalogosValoresDTO> lstCatalogosValores;

    /**
     * Campo itMantenible
     */
    private String itMantenible;
}
