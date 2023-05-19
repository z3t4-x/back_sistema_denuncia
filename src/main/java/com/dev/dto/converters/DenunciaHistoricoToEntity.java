package com.dev.dto.converters;

import com.dev.domain.DenunciaHistorico;
import com.dev.dto.DenunciaHistoricoDTO;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum DenunciaHistoricoToEntity implements Function<DenunciaHistoricoDTO, DenunciaHistorico> {

    INSTANCE;

    @Override
    public DenunciaHistorico apply(DenunciaHistoricoDTO dto) {
        DenunciaHistorico entity = new DenunciaHistorico();

        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);

            if (dto.getEstadoExpedienteAnterior() != null) {
                entity.setEstadoExpedienteAnterior(CatalogosValoresToEntity.INSTANCE.apply(dto.getEstadoExpedienteAnterior()));
            }
            if (dto.getEstadoExpedienteNuevo() != null) {
                entity.setEstadoExpedienteNuevo(CatalogosValoresToEntity.INSTANCE.apply(dto.getEstadoExpedienteNuevo()));
            }
        }

        return entity;
    }
}
