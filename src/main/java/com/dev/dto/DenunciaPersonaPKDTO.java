package com.dev.dto;

import lombok.Data;

@Data
public class DenunciaPersonaPKDTO {

    private DenunciaDTO denunciaDTO;
    private PersonaDTO personaDTO;

    public DenunciaPersonaPKDTO() {
        super();
        this.denunciaDTO = new DenunciaDTO();
        this.personaDTO = new PersonaDTO();
    }

}


