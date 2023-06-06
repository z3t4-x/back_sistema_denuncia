package com.dev.dto.converters;

import com.dev.domain.DenunciaHistorico;
import com.dev.dto.DenunciaHistoricoDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum DenunciaHistoricoToDTO implements Function<DenunciaHistorico, DenunciaHistoricoDTO> {

    INSTANCE;

    @Override
    public DenunciaHistoricoDTO apply(DenunciaHistorico entity) {
        DenunciaHistoricoDTO dto = new DenunciaHistoricoDTO();

        if (entity != null) {

            BeanUtils.copyProperties(entity, dto);
            this.replaceNull(dto, entity);

            if (entity.getEstadoExpediente() != null) {
                dto.setEstadoExpediente(CatalogosValoresToDTO.INSTANCE.apply(entity.getEstadoExpediente()));
            }

        }

        return dto;
    }


    private void replaceNull(DenunciaHistoricoDTO dto, DenunciaHistorico entity) {
        if (StringUtils.isBlank(entity.getNumDocumento())) {
            dto.setNumDocumento(StringUtils.EMPTY);
        }
        if (StringUtils.isBlank(entity.getDescripcion())) {
            dto.setDescripcion(StringUtils.EMPTY);
        }
    }

}
