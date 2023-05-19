package com.dev.dto;

import com.dev.domain.Denuncia;
import com.dev.domain.Persona;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DenunciaPersonaDTO {

    private DenunciaDTO denunciaDTO;
    private PersonaDTO personaDTO;

    private LocalDateTime fcAltaDenuncia;
    private CatalogosValoresDTO tipoPersona;

    public DenunciaPersonaDTO() {
        super();
        this.denunciaDTO = new DenunciaDTO();
        this.personaDTO = new PersonaDTO();
        this.tipoPersona = new CatalogosValoresDTO();
    }

}
