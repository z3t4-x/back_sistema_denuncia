package com.dev.dto;

import com.dev.domain.Denuncia;
import com.dev.domain.Persona;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DenunciaPersonaDTO {

    private Integer idDenunciaPersona;

    private Integer idDenuncia;

    private PersonaDTO personaDTO;

    private CatalogosValoresDTO tipoPersona;

    private String itBaja;

    public DenunciaPersonaDTO() {
        super();
        this.personaDTO = new PersonaDTO();
        this.tipoPersona = new CatalogosValoresDTO();
    }

}
