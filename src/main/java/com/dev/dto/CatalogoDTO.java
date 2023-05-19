package com.dev.dto;

import com.dev.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CatalogoDTO extends AuditoriaDTO implements Serializable {

    private String codigo;

    /**
     * Descripcion
     */
    private String descripcion;





    /**
     * Constructor
     */
    public CatalogoDTO() {
        super();
        this.codigo = StringUtils.EMPTY;
        this.descripcion = StringUtils.EMPTY;
    }

}

