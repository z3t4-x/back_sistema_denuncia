package com.dev.dto.converters;

import com.dev.domain.DenunciaPersonaPK;
import com.dev.dto.DenunciaPersonaPKDTO;

import java.util.function.Function;

public enum DenunciaPersonaPKToDTO implements Function<DenunciaPersonaPK, DenunciaPersonaPKDTO> {

    INSTANCE;

    @Override
    public DenunciaPersonaPKDTO apply(DenunciaPersonaPK entity) {
        DenunciaPersonaPKDTO dto = new DenunciaPersonaPKDTO();

        return dto;
    }
}

